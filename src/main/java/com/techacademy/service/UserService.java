//サービスの作成
package com.techacademy.service;

import java.util.List;
import java.util.Set; // 追加User削除
import org.springframework.stereotype.Service;
import com.techacademy.entity.User;
import com.techacademy.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional; // 追加

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository repository) {
		this.userRepository = repository;
	}

	/** 全件を検索して返す */
	public List<User> getUserList() {
		// リポジトリのfindAllメソッドを呼び出す
		return userRepository.findAll();
	}

	// ----- 追加:ここから -----
	/** Userを1件検索して返す */
	public User getUser(Integer id) {
		return userRepository.findById(id).get();
	}
	// -----User変更機能 追加:ここまで -----

	// ----- 追加:ここから -----
	/** Userの登録を行なう */
	@Transactional
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	// ----- User登録機能：サービスの修正追加:ここまで -----

	// ----- 追加:ここから -----
	/** Userの削除を行なう */
	@Transactional
	public void deleteUser(Set<Integer> idck) {
		for (Integer id : idck) {
			userRepository.deleteById(id);
		}
	}
	// ----- Userの削除追加:ここまで -----
}