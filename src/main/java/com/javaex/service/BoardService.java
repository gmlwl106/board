package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.CategoryDao;
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

	
	//카테고리 가져오기
	public List<CategoryVo> getCategory() {
		return cateDao.getCategory();
	}

	
	//게시글 등록
	public String write(PostVo postVo, List<MultipartFile> fileList) {
		postVo.setContent(postVo.getContent().replace("\r\n", "<br>")); //줄바꿈
		//게시글 등록
		int cnt = postDao.insertPost(postVo);
		postDao.insertPost(postVo);
		postDao.insertPost(postVo);
		postDao.insertPost(postVo);
		postDao.insertPost(postVo);
		
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
					String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
					//파일경로(디렉토리+저장파일명)
					String filePath = "C:\\workspace\\ean_board\\webapp\\assets\\files\\"+saveName;
					
					//DB 저장
					FileVo fileVo = new FileVo(postNo, saveName, filePath);
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
		int cnt = postDao.deletePost(no);
		
		if(cnt > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	

}
