package org.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.dao.MetadataDao;
import org.entity.Mp3;




public class UploadServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//upload
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart) { 			//jsp form have multipart attribute
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			//using parseRequest to solve all requested information in form, and save it in items list
			try {
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iterator = items.iterator();
				while(iterator.hasNext()) {
					FileItem item = iterator.next();
					//if(item.getFieldName().equals("music")){
					
						//get filename
						String fileName = item.getName();
						//get file content and upload
						
						//check mp3 format 
						String ext = fileName.substring(fileName.lastIndexOf(".")+1);
						if(!ext.equals("mp3")) {
							String showLine = "Wrong format for AudioFile, Please attach to mp3 file!";
							System.out.println(showLine);
							request.setAttribute("showLine", showLine);
							request.getRequestDispatcher("result.jsp").forward(request, response);
							return;
						}

						//get service path
						String path = "D:\\upload";
						File file = new File(path);
						if(!file.exists()) { //if no file
							file.mkdir();    //create file
						}
						file = new File(path,fileName);
						item.write(file);
						
						System.out.println(fileName +" : upload success£¡");
						
						//extract metadata
						Mp3 mp3 = new Mp3(path+"\\"+fileName);
						mp3.ExtractMp3();
						String songname = mp3.getSongName();
						String artist = mp3.getArtist();
						String album = mp3.getAlbum();
						String year = mp3.getYear();
						
						//Write to database
						//Invoke the update metadata function of the model layer
						int result = MetadataDao.update(mp3);
						if (result > 0) {		//update succeeded
							String message1 = "one metadata has been updata succeeded,";
							String message2 = "SongName="+songname+",artist="+artist+
							",album="+album+",year="+year;
							String message = message1+"\n"+message2;
							System.out.println(message);
							request.setAttribute("message", message);
							request.getRequestDispatcher("result.jsp").forward(request, response);
						} else {					//Failed to update
							String message = "Failed to update....";
							System.out.println(message);
							request.setAttribute("message", message);
							request.getRequestDispatcher("result.jsp").forward(request, response);
						}					
						return;
						
					//}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	

	
	
	
	
}
