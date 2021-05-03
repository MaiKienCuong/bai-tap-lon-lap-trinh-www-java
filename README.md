- xóa db cũ

- chạy script tạo db

account customer 1: anhem 123456
account customer 1: emem 123456
account admin:  admin admin

cho khách hàng sửa username với điều kiện không trùng
email không được trùng
address k nhập thì lưu null vào db


# đăng nhập POST - api/auth/signin
```diff
{
    "username": "admin",
    "password":"admin"
}
```

# đăng ký POST - api/auth/signup
```diff
{
    "name": "Cường Mai Kiên",
    "phone": "0961516942",
    "email": "kiencuongsasuke@gmail.com",
    "address":"Dương Quảng Hàm Gò Vấp TP Hồ Chí Minh",
    "account": {
        "username":"anhem",
        "password":"12345678"
    }
}
```

+ các tham số size, page, sort dùng để phân trang, nếu không truyền thì lấy giá trị mặc định, không bắt buộc phải truyền đủ 3 tham số

- tất cả loại sản phẩm GET api/categories?size=10&page=1&sort=name-asc

- loại sản phẩm theo id GET api/category/{id}

# thêm loại sản phẩm POST - api/category
```diff
{
    "name":"áo thu đông trẻ em nam"
}
```
# cập nhật loại sản phẩm PUT api/category
```diff
{
    "id":"5",
    "name":"áo thun trẻ em nam"
}
```
- xóa sản phẩm theo id DELETE api/category/{id}

- tất cả khách hàng GET api/customers?size=10&page=1&sort=name-asc

- khách hàng theo id GET api/customer/{id}

# khách hàng đăng ký mới: POST - api/customer
```diff
{
    "name": "Cường Mai Kiên",
    "phone": "0961516942",
    "email": "kiencuongsasuke@gmail.com",
    "address":"Dương Quảng Hàm Gò Vấp TP Hồ Chí Minh",
    "account": {
        "username":"anhem",
        "password":"12345678"
    }
}
```
# khách hàng sửa thông tin: PUT - api/customer
```diff
{
    "id":66,
    "name": "Cường Mai Kiên",
    "phone": "0961516942",
    "email": "kiencuongsasuke@gmail.com",
    "address":"Dương Quảng Hàm Gò Vấp TP Hồ Chí Minh",
    "account": {
        "username":"anhem",
        "password":"12345678",
        "enable": true,
    }
}
```
- xóa khách hàng DELETE - api/customer/{id}

- tất cả hóa đơn GET - api/orders?size=10&page=1&sort=orderDate-desc

# thêm hóa đơn POST - api/order
```diff
paymantMethod phải là COD hoặc STORE
{
   "shipAddress": "ha thanh ha trung thanh hoa city",
    "paymentMethod": "STORE",
    "customerId":63,
    "orderDetails": [
        {
            "quantity": 2,
            "price": 250000,
            "subProductId":8
        },
        {
            "quantity": 3,
            "price": 250000,
            "subProductId":10
        },
        {
            "quantity": 4,
            "price": 250000,
            "subProductId":16
        }
    ]
}
```

- lấy danh sách tất cả hóa đơn của 1 khách hàng GET - api/orders/customer/{id}?size=10&page=1&sort=total-desc

- xem chi tiết hóa đơn của hóa đơn theo mã hóa đơn GET - api/order-detail/order/{id}

- tất cả sản phẩm GET api/products?size=10&page=1&sort=price-desc

- tìm sản phẩm theo từ khóa GET api/product/search?q=áo thun

- tìm sản phẩm theo loại GET api/product/category?q=áo thun

- sản phẩm theo id GET api/product/{id}

- tất cả size của 1 sản phẩm GET api/product/sizes/{id}

- tất cả màu của 1 sản phẩm GET api/product/colors/{id}

- lấy size và số lượng tồn theo màu GET api/product/size-and-inventory?id=1&color=đỏ

- sản phẩm đang nhiều lượt xem GET api/product/marker?marker=HOT

- sản phẩm đang gairm giá GET api/product/marker?marker=DIS

- sản phẩm đang vừa hot vừa giảm giá GET api/product/marker?marker=HOT,DIS
