package org.entity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Mp3 {
	private static String charset = "gbk";
	private static String path="";
	//private  String sTagV1 = "";
	private Mp3TagV1 tagV1 = null;
	private String songName;
	private String artist;
	private String album;
	private String year;
	
	public Mp3(String path) {
		this.path = path;
	}
	

    public Mp3(String songName, String artist, String album, String year) {
		super();
		this.songName = songName;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}

    
	public void ExtractMp3() {
        try {
            String mp3 = path;
            byte[] buf = new byte[128];
            RandomAccessFile raf = new RandomAccessFile(mp3,"r");
            raf.seek(raf.length() - 128);
            raf.read(buf);
            raf.close();
            if (buf.length != 128){
                throw new Exception("MP3 tag is illegal!");
            }
            if(!"TAG".equalsIgnoreCase(new String(buf,0,3))){
                throw new Exception("MP3 tag structure is not correct!");
            }

            //sTagV1 = new String(buf, charset).trim();
            //System.out.println("sTagV1::=" + sTagV1);

            tagV1 = new Mp3TagV1();
            tagV1.songName = new String(buf, 3, 30, charset).trim();
            tagV1.artist = new String(buf, 33, 30, charset).trim();
            tagV1.album = new String(buf, 63, 30, charset).trim();
            tagV1.year = new String(buf, 93, 4, charset).trim();
            tagV1.comment = new String(buf, 97, 28, charset).trim();
            
            songName = tagV1.songName;
            artist = tagV1.artist;
            album = tagV1.album;
            year = tagV1.year;
            System.out.println("tagV1::=" + tagV1);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    

    public String getSongName() {
		return songName;
	}


	public void setSongName(String songName) {
		this.songName = songName;
	}


	public String getArtist() {
		return artist;
	}


	public void setArtist(String artist) {
		this.artist = artist;
	}


	public String getAlbum() {
		return album;
	}


	public void setAlbum(String album) {
		this.album = album;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	@Override
    public String toString() {
        return "tagV1::= "+tagV1;
    }
    


    static class Mp3TagV1{
        protected String songName;
        protected String artist;
        protected String album;
        protected String year;
        protected String comment;

        @Override
        public String toString() {
            return "Mp3TagV1 [songName=" + songName + ", artist=" + artist +", album="+ album + ", year=" +
                    year + ", comment=" + comment +"]";
        }
    }



    class FrameBodyTXXX{
        String description;
        String text;

        public void setDescription(String description) {
            this.description = description;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
