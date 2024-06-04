//エンティティの作成
package com.techacademy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
//@Data はLombokのアノテーションです。「getter/setter、toString、hashCode、equals」のメソッドを生成します。
@Entity
//@Entity はSpring JPAのアノテーションです。エンティティクラス（データベースのテーブルにマッピングするクラス）であることを示します。
@Table(name = "user")
//@Table もSpring JPAのアノテーションです。エンティティが対応する（紐づく）テーブル名を指定します。今回はMySQLの user テーブルに対応しています。

public class User {

    /** 性別用の列挙型 */
	///** で始まるコメントは「Javadoc」というコメントの書き方 コメントのポップアップ表示や、ドキュメントの自動生成を行なうことができる
    public static enum Gender {
        男性, 女性
    }

    /** 主キー。自動生成 */
    @Id
    //@Id はSpring JPAのアノテーションです。主キー
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue はSpring JPAのアノテーションです。主キーの値を一意に自動生成
    private Integer id;

    //GenerationType.AUTO：データベースに最適な方法が自動的に選択される。
    //GenerationType.IDENTITY：自動インクリメントで生成する（データベースがOracleの場合はSEQUENCEと同じ処理を行なう）。
    //GenerationType.SEQUENCE：シーケンスを使って生成する（データベースがMySQLの場合はTABLEと同じ処理を行なう）。
    //GenerationType.TABLE：シーケンス専用テーブルを使って生成する。

    /** 名前。20桁。null不許可 */
    @Column(length = 20, nullable = false)
    private String name;

    /** 性別。2桁。列挙型（文字列） */
    @Column(length = 2)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /** 年齢 */
    private Integer age;

    /** メールアドレス。50桁。null許可 */
    @Column(length = 50)
    private String email;

    //@Column はSpring JPAのアノテーションです。フィールドをマッピングするテーブルのカラム（項目）を指定します。今回は使用していませんが、name="カラム名"でマッピングするカラム名を指定できます。name属性を省略した場合、フィールド名がマッピングするカラム名となります。属性の意味は以下のとおりです。
    //length : フィールドの桁数を指定します。桁数指定ができるフィールドは文字列型か配列型のみです。数値型は指定できません。
    //nullable フィールドのNOT NULL制約を指定します。値が「true」のときはnull値を許可し、「false」のときはnull値を許可しません。デフォルト値は「true」です。
    //@Enumerated はSpring JPAのアノテーションです。このフィールドの型が列挙型であることを指定します。 EnumType.STRING 属性でテーブルのカラムを文字列型に指定します。これにより、カラムには「男性」などの列挙子の名前が格納されます。


}