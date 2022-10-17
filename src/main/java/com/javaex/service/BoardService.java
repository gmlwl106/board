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

	
	//게시글 가져오기
	public Map<String, Object> getPost(int no) {
		//조회수 올리기
		postDao.updateHit(no);
		
		//게시글 상세내용 가져오기
		Map<String, Object> post = postDao.getPost(no);
		List<FileVo> fileList = fileDao.getFile(no);
		
		//map에 담기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("post", post);
		map.put("fileList", fileList);
		//댓글
		
		return map;
	}

	
	//게시글 리스트 가져오기
	public List<Map<String, Object>> getPostList() {
		return postDao.getPostList();
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
