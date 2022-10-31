package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.CategoryDao;
import com.javaex.dao.CommentDao;
import com.javaex.dao.FileDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.FileVo;
import com.javaex.vo.PostVo;

@Service
public class BoardService {
	
	@Autowired
	private CategoryDao cateDao;
	@Autowired
	private PostDao postDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private CommentDao cmtDao;

	
	//카테고리 가져오기
	public List<CategoryVo> getCategory() {
		return cateDao.getCategory();
	}

	
	//게시글 등록
	public String write(PostVo postVo, List<MultipartFile> fileList) {
		postVo.setContent(postVo.getContent().replace("\r\n", "<br>")); //줄바꿈
		//게시글 등록
		int cnt = postDao.insertPost(postVo);
		
		if(cnt > 0) {
			//파일 등록
			if(fileList.size() > 0) {
				
				int postNo = postVo.getPostNo(); //게시글 번호
	
				for(MultipartFile file : fileList) {
					//오리지날 파일명
					String orgName = file.getOriginalFilename();
					//확장자
					String exName = orgName.substring(orgName.lastIndexOf("."));
					//현재시간+랜던UUID+확장자
					String sysName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
					//파일경로(디렉토리+저장파일명)
					String filePath = "C:\\workspace\\ean_board\\webapp\\assets\\files\\"+sysName;
					
					//DB 저장
					FileVo fileVo = new FileVo(postNo, orgName, filePath);
					int count = fileDao.insertFile(fileVo);
					
					if(count > 0) {
						//파일 저장
						try {
							
							byte[] fileData = file.getBytes();
							OutputStream os = new FileOutputStream(filePath);
							BufferedOutputStream bos = new BufferedOutputStream(os);
							
							bos.write(fileData);
							bos.close();
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return "fail";
						}
					} else {
						return "fail";
					}
				}
			}	
			
			return "success";
			
		} else {
			return "fail";
		}
	}
	
	
	//조회수 올리기
	public void viewHitUp(int no) {
		postDao.updateHit(no);
	}

	
	//게시글 가져오기
	public Map<String, Object> getPost(int no) {
		
		//게시글 상세내용 가져오기
		Map<String, Object> post = postDao.getPost(no);
		List<FileVo> fileList = fileDao.getFile(no);
		
		//map에 담기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("post", post);
		map.put("fileList", fileList);
		
		return map;
	}

	
	//게시글 리스트 가져오기 (+검색)
	public Map<String, Object> getPostList(int crtPage, String kwdOpt, String keyword) {
		
		//페이지당 글 갯수
		int listCnt = 10;
		
		//현재페이지
		crtPage = (crtPage>0)? crtPage : (crtPage=1);
		
		int startRnum = (crtPage-1)*listCnt+1; //시작번호
		int endRnum = (startRnum + listCnt)-1; //끝번호
		
		//파라미터 맵
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		map.put("kwdOpt", kwdOpt);
		map.put("keyword", keyword);
		
		//게시글 리스트
		List<Map<String, Object>> postList = postDao.getPostList(map);
		
		//=========================페이징 계산=========================
		//전체글 갯수
		int totalCnt = postDao.getTotalCnt(map);
		System.out.println("전체글갯수:"+totalCnt);
		
		//페이지당 버튼 갯수
		int pageBtnCnt = 5;
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil(crtPage / (double)pageBtnCnt)*pageBtnCnt;
		
		//시작 버튼 번호
		int startPageBtnNo = (endPageBtnNo-pageBtnCnt) + 1;
		
		//다음 화살표 유무
		boolean next = false;
		if((listCnt*endPageBtnNo) < totalCnt) {
			next = true;
		} else {
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		Map<String, Object> postMap = new HashMap<String, Object>();
		postMap.put("postList", postList);
		postMap.put("prev", prev);
		postMap.put("next", next);
		postMap.put("startPageBtnNo", startPageBtnNo);
		postMap.put("endPageBtnNo", endPageBtnNo);
		
		return postMap;
	}


	//게시글 수정 폼
	public Map<String, Object> modifyForm(int no) {
		Map<String, Object> post = postDao.getPost(no);
		post.replace("CONTENT", post.get("CONTENT").toString().replace("<br>", "\r\n")); //줄바꿈
		return post;
	}


	//게시글 수정
	public int modify(PostVo postVo) {
		postVo.setContent(postVo.getContent().replace("\r\n", "<br>"));
		return postDao.updatePost(postVo);
	}


	//게시글 삭제
	public String delete(int no) {
		
		//파일 삭제
		fileDao.deleteFile(no);
		
		//댓글 삭제
		cmtDao.deleteCmt(no);
		
		//게시글 삭제
		int cnt = postDao.deletePost(no);
		
		if(cnt > 0) {
			return "success";
		} else {
			return "fail";
		}
	}


	//통계 데이터 가져오기
	public JSONArray getData(String month) {
		String domain = "http://192.168.0.17:8088/JOA/api/stat?month="+month;
		
		try {
			
			/* URLConnection 이용해서 데이터 가져옴 */
			
			//url 객체 생성
			URL url = new URL(domain);
			//connection 생성
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//요청 메소드 설정
			conn.setRequestMethod("GET");
			
			//요청 설정
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept-Language", "ko-kr");
            conn.setRequestProperty("Access-Control-Allow-Origin", "*");
            conn.setRequestProperty("Content-Type", "application/json");
			
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.connect();
			
			OutputStream out = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out);
			BufferedWriter bw = new BufferedWriter(osw);
			
			InputStream in = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);
			
			
			String data = br.readLine();
			bw.write(data);
			System.out.println(data);

			bw.close();
			br.close();
			
			
			/* 가져온 데이터를 가공 */
			JSONParser parser = new JSONParser();
			JSONArray arr = null; //list 형태로 되어있을때 JSONArray를 사용
			//JSONObject obj = null; //object 형태로 되어있을때 사용
			
			arr = (JSONArray) parser.parse(data); //data를 map의 형태로 만들어줌
			
			return arr;

		} catch (MalformedURLException e) {
			System.out.println(domain + " is not a URL I understand");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	

}
