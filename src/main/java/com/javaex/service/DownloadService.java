package com.javaex.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.FileDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.FileVo;

@Service
public class DownloadService {

	@Autowired
	private FileDao fileDao;
	@Autowired
	private PostDao postDao;
	
	//파일 다운로드
	public void fileDownload(HttpServletResponse response, int no) throws Exception {
		//파일 경로 가져오기
		String path = fileDao.getFilePath(no);
		
		try {
			
		File file = new File(path);
		response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
		// 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
		
		
		FileInputStream fileInputStream = new FileInputStream(path); // 파일 읽어오기 
		OutputStream out = response.getOutputStream();
		
		int read = 0;
		byte[] buffer = new byte[1024];
		//1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
		while ((read = fileInputStream.read(buffer)) != -1) { 
			out.write(buffer, 0, read);
		}
		
		fileInputStream.close();
		        
		} catch (Exception e) {
			throw new Exception("download error");
		}
	}

	//게시글 다운로드
	public void postDownload(int no, HttpServletResponse response) throws Exception {
		
		//게시글 정보 가져오기
		Map<String, Object> post = postDao.getPost(no);
		post.replace("CONTENT", post.get("CONTENT").toString().replace("<br>", "\n")); //줄바꿈
		List<FileVo> files = fileDao.getFile(no);
		String fileNames = ""; //파일 이름
		if(files.size() > 0) {
			for(FileVo file : files) {
				fileNames += file.getSaveName() + ",\n";
			}
			fileNames.substring(0, fileNames.length() - 3);
		} else {
			fileNames += "파일 없음";
		}
		
		
		////////////////////////////////엑셀파일 생성/////////////////////////////////////////////////////
		//.xlsx 확장자 지원
		XSSFWorkbook workbook = null; 
		XSSFSheet sheet = null; 
		XSSFRow row = null; 
		XSSFCell cell = null;
			
		try {
			int rowNo = 0; // 행의 갯수 
			
			String[] cellNames = {"카테고리", "제목", "내용", "작성자", "조회수", "작성일", "첨부파일"};
			String[] keys = {"CATENAME", "TITLE", "CONTENT", "NAME", "HIT", "REGDATE", "FILE"};
			
			workbook = new XSSFWorkbook(); //XSSFWorkbook 객체 생성
			sheet = workbook.createSheet("post"); // 워크시트 이름 설정
			
			//테이블 스타일 설정
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setBorderTop((short) 1);    // 테두리 위쪽
			cellStyle.setBorderBottom((short) 1); // 테두리 아래쪽
			cellStyle.setBorderLeft((short) 1);   // 테두리 왼쪽
			cellStyle.setBorderRight((short) 1);  // 테두리 오른쪽
			
			sheet.setColumnWidth(1, (sheet.getColumnWidth(1))+(short)1000000); // 1번째 컬럼 넓이 조절
			cellStyle.setWrapText(true); //줄바꿈 적용
			
			for(int i=0; i<cellNames.length; i++) {
				row = sheet.createRow(rowNo++);
				cell = row.createCell((short) 0);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(cellNames[i]);
				cell = row.createCell((short) 1);
				cell.setCellStyle(cellStyle);
				if(keys[i].equals("FILE")) {
					cell.setCellValue(fileNames);
				} else {
					cell.setCellValue(post.get(keys[i]).toString());
				}
				
			}
			
			////////////////////////////////파일 저장/////////////////////////////////////////////////////
			//저장위치
			String localPath = "C:\\workspace\\ean_board\\webapp\\assets\\files\\";
			//현재시간+랜던UUID+확장자
			String saveName = "[POST]"+System.currentTimeMillis() + UUID.randomUUID().toString() + ".xlsx";
			//파일 경로
			String filePath = localPath + saveName;
			
			
			File file = new File(filePath);
			response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
			// 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
			
			OutputStream out = response.getOutputStream();

			workbook.write(out);
	
		}catch(Exception e){
	        	
		}
	}
	
	
}
