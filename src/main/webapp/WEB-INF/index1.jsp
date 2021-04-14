<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>resful web api ban quan ao tre em</h1>
	<br> 1. HIỆN DANH SÁCH SP Ở TRANG CHỦ
	<br>
	<mark>api/products</mark>
	<a href="api/products"> tất cả</a>
	<hr>
	<br>
	<mark>api/products?sort=price-desc</mark>
	<a href="api/products?sort=price-desc">sắp xếp theo giá giảm dần</a>
	<br>
	<mark>api/products?sort=price-asc</mark>
	<a href="api/products?sort=price-asc">sắp xếp theo giá tăng dần</a>
	<hr>
	<br>
	<mark>api/products?sort=name-asc</mark>
	<a href="api/products?sort=name-asc">sắp xếp theo tên A->Z</a>
	<br>
	<mark>api/products?sort=name-desc</mark>
	<a href="api/products?sort=name-desc">sắp xếp theo tên Z->A</a>
	<hr>
	<br>
	<mark>api/products?sort=createdAt-desc</mark>
	<a href="api/products?sort=createdAt-desc">sắp xếp theo sản phẩm
		mới nhất</a>
	<br>
	<mark>api/products?sort=createdAt-asc</mark>
	<a href="api/products?sort=createdAt-asc">sắp xếp theo sản phẩm cũ
		nhất</a>
	<hr>
	<br>
	<mark>api/products?sort=views-desc</mark>
	<a href="api/products?sort=views-desc">sắp xếp theo views giảm dần</a>
	<hr>
	<br>
	<mark>api/product/search?q=áo thun</mark>
	<a href="api/product/search?q=áo thun">tìm sản phẩm với từ khóa áo
		thun</a>
	<br>
	<mark>api/product/search?q=váy</mark>
	<a href="api/product/search?q=váy">tìm sản phẩm với từ khóa váy</a>
	<br>
	<mark>api/product/search?q=quần</mark>
	<a href="api/product/search?q=quần">tìm sản phẩm với từ khóa quần</a>
	<hr>
	<br>
	<mark>api/product/category/?q=áo</mark>
	<a href="api/product/category/?q=áo">loại sản phẩm: áo</a>
	<br>
	<mark>api/product/category/?q=quần</mark>
	<a href="api/product/category/?q=quần">loại sản phẩm: quần</a>
	<br>
	<mark>api/product/category/?q=váy</mark>
	<a href="api/product/category/?q=váy">loại sản phẩm: váy</a>
	<hr>
	<br>
	<mark>api/product/marker/?marker=HOT</mark>
	<a href="api/product/marker/?marker=HOT">marker = hot</a>
	<br>
	<mark>api/product/marker/?marker=DIS</mark>
	<a href="api/product/marker/?marker=DIS">marker = discount</a>
	<br>
	<mark>api/product/marker/?marker=DIS&marker=DIS</mark>
	<a href="api/product/marker/?marker=HOT&marker=DIS">marker = hot or
		discount</a>
	<hr>
	<br>
	<br> KHÁCH: CHỌN SẢN PHẨM 1, HIỆN CHI TIẾT SP
	<br>
	<mark>api/product/1</mark>
	<a href="api/product/1">san pham 1</a>
	<hr>
	<br> LẤY RA TẤT CẢ MÀU VÀ SIZE CỦA SẢN PHẨM ĐÓ VÀ HIỂN THỊ
	<br>
	<mark>api/product/colors/1</mark>
	<a href="api/product/colors/1">tat ca COLOR cua san pham 1</a>
	<br>
	<mark>api/product/sizes/1</mark>
	<a href="api/product/sizes/1">tat ca SIZE cua san pham 1</a>
	<br>
	<hr>
	<br> KHÁCH: CHỌN 1 MÀU, LẤY RA TẤT CẢ CÁC SIZE CÙNG SỐ LƯỢNG TỒN,
	CÁI NÀO SỐ LƯỢNG TỒN =0 THÌ DISABLE
	<br>
	<mark>api/product/size-and-inventory?id=1&color=Đỏ</mark>
	<a href="api/product/size-and-inventory?id=1&color=Đỏ">size va so
		luong ton cua mau ĐỎ</a>
	<br>
	<mark>api/product/size-and-inventory?id=1&color=Xanh</mark>
	<a href="api/product/size-and-inventory?id=1&color=Xanh">size va so
		luong ton cua mau XANH</a>
	<br>
	<hr>
	<br>
	<mark>api/categories</mark>
	<a href="api/categories">tất cả loại sản phẩm</a>
	<br>
	<mark>api/orders</mark>
	<a href="api/orders">tất cả hóa đơn</a>

</body>
</html>