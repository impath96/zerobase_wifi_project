package com.zerobase.wifi.service;

import com.zerobase.wifi.dao.BookmarkDao;
import com.zerobase.wifi.dto.BookmarkDto;

import java.util.List;

public class BookmarkService {

	private BookmarkDao bookmarkDao;
	
	// 북마크 저장
	public int save(String wifiMgrNo, Long bookmarkGroupId) {
		bookmarkDao = new BookmarkDao();
		return bookmarkDao.insert(wifiMgrNo, bookmarkGroupId);
	}
	
	// 모든 북마크 가져오기
	public List<BookmarkDto> getAllBookmark() {
		bookmarkDao = new BookmarkDao();
		return bookmarkDao.selectAll();
	}

    public BookmarkDto getBookmark(Long id) {
		bookmarkDao = new BookmarkDao();
		return bookmarkDao.selectById(id);
    }

	public int delete(Long id) {
		bookmarkDao = new BookmarkDao();
		return bookmarkDao.delete(id);
	}
}
