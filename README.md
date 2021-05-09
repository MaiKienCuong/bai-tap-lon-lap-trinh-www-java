# đổi mật khẩu PUT -api/account
```diff
{
    "username":"fff",
    "oldPassword":"1234567",
    "newPassword":"123456"
}
```

# đăng nhập POST - api/auth/signin
```diff
{
    "username": "admin",
    "password":"admin"
}
```

# đăng ký tài khoản cho khách hàng mới POST - api/auth/signup
```diff
{
    "name": "Cường Mai Kiên",
    "phone": "1234567890",
    "email": "cuong@gmail.com",
    "address":"Gò Vấp TP Hồ Chí Minh",
    "account": {
        "username":"anhem",
        "password":"123456"
    }
}
```

+ các tham số size, page, sort dùng để phân trang, nếu không truyền thì lấy giá trị mặc định, không bắt buộc phải truyền đủ 3 tham số

# tất cả loại sản phẩm GET - api/categories
```diff
```

# loại sản phẩm theo id GET - api/category/{id}
```diff
```

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
# xóa loại sản phẩm theo id DELETE - api/category/{id}
```diff
```

# tất cả khách hàng GET - api/customers
```diff
```

# khách hàng theo id GET - api/customer/{id}
```diff
```

# khách hàng đăng ký mới : POST - api/customer - giống với api/signup
```diff
{
    "name": "Cường Mai Kiên",
    "phone": "1234567890",
    "email": "cuong@gmail.com",
    "address":"Gò Vấp TP Hồ Chí Minh",
    "account": {
        "username":"anhem",
        "password":"123456"
    }
}
```
# cập nhật thông tin khách hàng: PUT - api/customer
```diff
{
    "id":66,
    "name": "Cường Mai Kiên",
    "phone": "1234567890",
    "email": "cuong@gmail.com",
    "address":"Gò Vấp TP Hồ Chí Minh",
    "account": {
        "username":"anhem",
        "password":"123456"
    }
}
```

# tất cả hóa đơn GET - api/orders
```diff
```

# thêm hóa đơn POST - api/order
```diff
paymantMethod phải là COD hoặc STORE
{
   "shipAddress": "ho chi minh",
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

# lấy danh sách tất cả hóa đơn của 1 khách hàng GET - api/orders/customer/{id}
```diff
```

# danh sách hóa đơn đang chờ xử lý GET - api/orders?status=PENDING
```diff
```

# danh sách hóa đơn đã hoàn tất GET - api/orders?status=COMPLETED
```diff
```

# hoặc GET - api/orders?status=PROCESSING&status=PENDING
```diff
```

# xem chi tiết hóa đơn của 1 hóa đơn theo mã GET - api/order-detail/order/{id}
```diff
```

# tất cả sản phẩm GET api/products
```diff
```

# thêm sản phẩm
```diff
{
    "name": "Áo thun nam kẻ sọc",
    "price": 12,
    "marker": "121",
    "discount": 121,
    "origin": "asa",
    "tax": 12,
    "shortDescription": "12!",
    "longDescription": "asda",
    "material": "1221",
    "supplierId": 1,
    "categoryId": 2,
    "subProducts": [
        {
            "name": "Áo thun nam kẻ sọc màu Đỏ size M",
            "color": "Đỏ",
            "size": "M",
            "inventory": 12
        },
        {
            "name": "Áo thun nam kẻ sọc màu Đỏ size L",
            "color": "Đỏ",
            "size": "L",
            "inventory": 12
        }
    ],
    "imagesUrl":[
        {
            "url": "sadmaksndnand"
        },
        {
            "url": "sadmaksndnanddmkm"
        }
    ]
}
```

# tìm sản phẩm theo từ khóa GET - api/product/search?q=áo thun
```diff
```

# tìm sản phẩm theo loại GET - api/product/category?q=áo thun
```diff
```

# sản phẩm theo id GET - api/product/{id}
```diff
```

# tất cả size của 1 sản phẩm GET - api/product/sizes/{id}
```diff
kết quả
[
    {
        "size": "L"
    },
    {
        "size": "M"
    }
]
```

# tất cả màu của 1 sản phẩm GET - api/product/colors/{id}
```diff
kết quả
[
    {
        "color": "Xanh"
    },
    {
        "color": "Đỏ"
    }
]
```

# lấy size và số lượng tồn theo màu GET - api/product/size-and-inventory?id=1&color=đỏ
```diff
kết quả
[
    {
        "size": "M",
        "inventory": 11,
        "subProductId": 1
    },
    {
        "size": "L",
        "inventory": 12,
        "subProductId": 2
    }
]
```

# sản phẩm đang nhiều lượt xem GET api/product/marker?marker=HOT
```diff
```

# sản phẩm đang gairm giá GET api/product/marker?marker=DIS
```diff
```

# sản phẩm đang vừa hot vừa giảm giá GET api/product/marker?marker=HOT,DIS
```diff
```

# tất cả nhà cung cấp GET - api/suppliers
```diff
```

# nhà cung cấp theo id GET - api/supplier/{id}
```diff
```

# thêm nhà cung cấp POST - api/supplier
```diff
{
    "name": "may việt tiến",
    "phone": "1234567890",
    "email": "maikiencuongiuh@gmail.com",
    "address": "Hồ Chí Minhhhhhhhhhhhhhhhhhhhh"
}
```

# cập nhật nhà cung cấp PUT - api/supplier
```diff
{
    "id":1,
    "name": "may việt tiến",
    "phone": "1234567890",
    "email": "maikiencuongiuh@gmail.com",
    "address": "Hồ Chí Minhhhhhhhhhhhhhhhhhhhh"
}
```

# xóa nhà cung cấp DELETE - api/supplier/{id}
```diff
```
