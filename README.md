# bai-tap-lon-lap-trinh-www-java

- xoa database cu
- chay script tao database truoc khi chay code

# khách hàng đăng ký mới: POST - api/customer
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
# khách hàng sửa thong tin: PUT - api/customer
{
    "name": "Cường Mai Kiên",
    "phone": "0961516942",
    "email": "kiencuongsasuke@gmail.com",
    "address":"Dương Quảng Hàm Gò Vấp TP Hồ Chí Minh",
    "account": {
        "password":"12345678"
    }
}

