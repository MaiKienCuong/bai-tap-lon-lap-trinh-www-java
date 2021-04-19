- xoa database cu
- chay script tao database truoc khi chay code

# đăng nhập POST - api/auth/signin
{
    "username": "admin",
    "password":"admin"
}

account customer 1: anhem 123456
account customer 1: emem 123456
account admin:  admin admin

cho khách hàng sửa username với điều kiện không trùng
email không được trùng
address k nhập thì lưu null vào db

# khách hàng đăng ký mới: POST - api/customer hoặc POST - api/auth/signup
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
# khách hàng sửa thông tin: PUT - api/customer
{
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

# thêm hóa đơn POST - api/order
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

# lấy danh sách tất cả hóa đơn của 1 khách hàng GET - api/orders/customer/{id}

# xem chi tiết hóa đơn của hóa đơn theo mã hóa đơn GET - api/order-detail/order/{id}
