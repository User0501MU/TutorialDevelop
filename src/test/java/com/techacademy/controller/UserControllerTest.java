//テスト
package com.techacademy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.techacademy.entity.User;

//import com.techacademy.entity.User;$

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;

    UserControllerTest(WebApplicationContext context) {
        this.webApplicationContext = context;
    }

    @BeforeEach
    //@BeforeEach アノテーションを付けると、各テストの前に、この処理が実行されます。本プロジェクトではSpring Securityを使用しているため、各テストの前に有効化
    void beforeEach() {
        // Spring Securityを有効にする
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity()).build();
    }

    //GetUserメソッド
    @Test
    @DisplayName("User更新画面")
    //@DisplayName でJUnitビューに表示されるテスト名を設定
    @WithMockUser
    void testGetUser() throws Exception {
        // HTTPリクエストに対するレスポンスの検証
        MvcResult result = mockMvc.perform(get("/user/update/1/")) // URLにアクセス
            .andExpect(status().isOk()) // ステータスを確認200OK
            .andExpect(model().attributeExists("user")) // Modelの内容を確認
            .andExpect(model().hasNoErrors()) // Modelのエラー有無の確認
            .andExpect(view().name("user/update")) // viewの確認
            .andReturn(); // 内容の取得.。内容は result 変数に格納されます。
        // userの検証
        // Modelからuserを取り出し検証を行っている
        User user = (User)result.getModelAndView().getModel().get("user");
        assertEquals(1, user.getId());//★単数形
        assertEquals("キラメキ太郎", user.getName());

    }
    //▷
    @Test
    @DisplayName("User一覧画面")
    //@DisplayName でJUnitビューに表示されるテスト名を設定
    @WithMockUser
    //★GetListメソッド課題
    void testGetList() throws Exception {
    	MvcResult result = mockMvc.perform(get("/user/list")) // URLにアクセス　//疑似的に動かすmock
                .andExpect(status().isOk()) // ステータスを確認200OK＝レスポンス正常値が200・404帰ってくる値無いよ
                .andExpect(model().attributeExists("userlist")) // Modelの内容を確認
                .andExpect(model().hasNoErrors()) // Modelのエラー有無の確認
                .andExpect(view().name("user/list"))//viewの確認：課題
                .andReturn(); // 内容の取得.。内容は result 変数に格納されます

    	//▶getUserListのリストはどんな形？複数存在するコレクション　形の探し方はserviceパッケージの中にあるUserServiceを見た
    	//▶上のresult（リザルド）からModelのUserlistを変数化した　項目75＝項目82に置き換わり　検証するために変数化
    	List<User> userlist = (List<User>)result.getModelAndView().getModel().get("userlist");

    	//リストに入っている件数が3件かどうかを検証※
    	//sizeというメソッドで要素数を数えます。このsizeメソッドの戻り値はintです。
    	assertEquals(userlist.size(),3);

    	//▷要素数を数えます　lesson8 > al.size();
        //int listCount = al.size();
        //System.out.println(listCount);

        //検証する内容運用保守のイメージ3回検証したことになる。

    	//▷テストコード部位ごとにフォーカスを当てるので、DBから実際に取るのではなく、※モックで本来出したい値を疑似的に値を返す
    	//J　unit　モック→検索。assertEquals
    	//MySQL接続していると権限がそちらに持ってかれて、JUnitエラーが起きたので実行の際は、注意
    	assertEquals(userlist.get(0).getId(),1);//,カンマ区切りで前と後を一致しているか確認
        assertEquals(userlist.get(0).getName(),"キラメキ太郎");//★コレクションの複数形どれすか？左：テストの対象vs検査する値

        assertEquals(userlist.get(1).getId(),2);//,カンマ区切りで前と後を一致しているか確認
        assertEquals(userlist.get(1).getName(),"キラメキ次郎");//★コレクションの複数形どれすか？左：テストの対象vs検査する値

        assertEquals(userlist.get(2).getId(),3);//,カンマ区切りで前と後を一致しているか確認
        assertEquals(userlist.get(2).getName(),"キラメキ花子");//★コレクションの複数形どれすか？左：テストの対象vs検査する値

    }

}