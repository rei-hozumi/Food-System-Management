# **Food System Management**



## **要件定義**

1\. システム概要

食品工場（焼き菓子）の製品・原材料・受発注・製造計画を管理し、業務の効率化を目的としたWebシステム

<br>

2\. システムの目的

＊現在、紙やExcelで管理している情報をシステム化する。

＊製品情報、原材料情報、受発注情報、製造計画の管理を一元化する。

<br>


3\. 利用者

| 利用者 | 業務 |
|--------|------|
| 管理者 | マスタ管理・全データ管理 |
| 計画担当者 | 受発注登録・製造計画登録 |
| 製造者 | 製造|

<br>




## **機能要件**

①ログイン・新規登録・ログアウト機能

②製品マスタ管理（一覧表示・CRUD）

（管理項目：製品コード・製品名・単価・内容量）

③原材料マスタ管理（一覧表示・CRUD）

（管理項目：原材料コード・原材料名・在庫数・単位（kg・袋・個など）

④受発注管理（一覧表示・CRUD）

（管理項目：受発注番号・製品・数量・納期・取引先・状態未処理or製造中or完了）

⑤製造計画管理（一覧表示・CRUD）

（管理項目：計画番号・製品・製造予定日・製造数量・担当者・状態未着手or製造中or完了）

⑥検索機能

＊各画面で検索できる。

・製品マスタ（製品コード・製品名）

・原材料マスタ（原材料コード・原材料名）

・受発注（受発注番号・製品名）

・状態（製造計画・計画番号・製品名・状態）

⑦入力チェック

＊必須入力

＊数値のみ入力

＊数量は0より大きい

＊コード重複チェック



<br>


## **非機能要件**

### 開発環境

＊OS：Windows11

＊IDE：Eclipse

＊言語：Java

＊ビルドツール：Maven

＊DB・管理ツール：MySQL,MySQL Workbench

＊フレームワーク：Spring Boot

＊サーバー：Tomcat

＊ブラウザ：Microsoft edge

＊コード管理：GitHub

<br>


##3 画面一覧

| 画面ID | 画面名 |
|--------|--------|
| SCR001 | ログイン |
| SCR002 | メニュー（メインページ） |
| SCR003 | 商品一覧 |
| SCR004 | 商品登録・編集 |
| SCR005 | 原材料一覧 |
| SCR006 | 原材料登録・編集 |
| SCR007 | 受注一覧 |
| SCR008 | 受注登録・編集 |
| SCR009 | 製造計画一覧 |
| SCR010 | 製造計画登録・編集 |

<br>


### テーブル構成

テーブル	内容

users				ユーザー

products			製品

materials			原材料

orders			受発注

production\_plans	製造計画


<br>

### テーブル設計

#### USERS　TABLE

|カラム|型|内容|
|-|-|-|
|id|BIGINT|PK|
|login\_id|VARCHAR(10)|ログインID|
|password|VARCHAR(255）|パスワード（ハッシュ化）|
|user\_name|VARCHAR(50)|氏名|
|role|VARCHAR(50)|部門（ADMIN,PLANNER,WORKER）|

<br>


#### PRODUCTS　TABLE

|カラム|型|内容|
|-|-|-|
|id|BIGINT|PK|
|product\_code|VARCHAR(30)|製品コード|
|product\_name|VARCHAR(50）|製品名|
|price|DECIMAL（5，2）|単価|


<br>



#### MATERIALS　TABLE

|カラム|型|内容|
|-|-|-|
|id|BIGINT|PK|
|material\_code|VARCHAR(30)|原材料コード|
|material\_name|VARCHAR(50）|原材料名|
|price|DECIMAL（5，2）|単価|
|expiration\_date|DATE|賞味期限|
|unit|VARCHAR(10）|単位|
|stock\_quantity|INT|在庫数|


<br>

#### ORDERS　TABLE

|カラム|型|内容|
|-|-|-|
|id|BIGINT|PK|
|order\_number|VARCHAR(30) UNIQUE|受注番号|
|order\_date|DATE|受注日|
|due\_date|DATE|納期|
|status|VARCHAR(10)|受注ステータス(受付・製造中・完了)|
|created\_by|BIGINT|FK:users(id)|
|product\_id|BIGINT|FK:products(id)|
|order\_quantity|INT|受注数量|

<br>




#### PRODUCTION\_PLANS　TABLE

|カラム|型|内容|
|-|-|-|
|id|BIGINT|PK|
|order\_id|BIGINT|FK:orders(id)|
|product\_id|BIGINT|FK:products(id)|
|planned\_quantity|INT|製造数量|
|assigned\_user\_id|BIGINT|FK:users(id)|
|status|VARCHAR(10)|製造ステータス(未着手・製造中・完了)|


<br>



## 権限制御

| 機能 | ADMIN | PLANNER | WORKER |
|------|:-----:|:--------:|:------:|
| 商品管理 | ○ | ○ | × |
| 原材料管理 | ○ | ○ | × |
| ユーザー管理 | ○ | × | × |
| 受注管理 | ○ | ○ | 一覧のみ |
| 製造計画一覧 | ○ | ○ | ○ |
| 製造計画登録 | ○ | ○ | × |
| 製造計画編集 | ○ | ○ | × |
| 製造計画削除 | ○ | ○ | × |

