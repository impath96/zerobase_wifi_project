package com.zerobase.wifi.service;

import com.zerobase.wifi.dao.BookmarkGroupDao;
import com.zerobase.wifi.dto.BookmarkGroupDto;

import java.util.List;

public class BookmarkGroupService {
	
	private BookmarkGroupDao bookmarkGroupDao;
	
	// 모든 북마크 그룹 가져오기
	public List<BookmarkGroupDto> getAllBookmarkGroup() {
		bookmarkGroupDao = new BookmarkGroupDao();
		return bookmarkGroupDao.selectAll();
	}
	
	// 북마크 그룹 저장
	public int save(BookmarkGroupDto bookmarkGroup) {
		bookmarkGroupDao = new BookmarkGroupDao();
		return bookmarkGroupDao.insert(bookmarkGroup);
	}
	
	// 특정 ID 북마크 그룹 가져오기
	public BookmarkGroupDto getBookmarkGroup(Long id) {
		bookmarkGroupDao = new BookmarkGroupDao();
		return bookmarkGroupDao.selectById(id);
	}
	
	// 북마크 그룹 수정
	public int edit(BookmarkGroupDto bookmarkGroup) {
		bookmarkGroupDao = new BookmarkGroupDao();
		return bookmarkGroupDao.update(bookmarkGroup);
	}
	
	// 북마크 그룹 수정
	public int delete(Long id) {
		bookmarkGroupDao = new BookmarkGroupDao();
		return bookmarkGroupDao.delete(id);
	}
}
