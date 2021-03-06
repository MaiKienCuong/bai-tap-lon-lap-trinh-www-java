USE master
GO
CREATE DATABASE [QuanAoTreEm]
GO
USE [QuanAoTreEm]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 06/02/2021 12:47:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Account](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[enable] [bit] NOT NULL,
	[password] [varchar](255) NOT NULL,
	[username] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Account_Role]    Script Date: 06/02/2021 12:47:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account_Role](
	[account_id] [bigint] NOT NULL,
	[role_id] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[account_id] ASC,
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Category]    Script Date: 06/02/2021 12:47:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Customer]    Script Date: 06/02/2021 12:47:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Customer](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[address] [nvarchar](255) NULL,
	[email] [varchar](50) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[phone] [varchar](50) NOT NULL,
	[account_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ImagesUrl]    Script Date: 06/02/2021 12:47:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ImagesUrl](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[url] [nvarchar](1000) NULL,
	[product_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 06/02/2021 12:47:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[price] [float] NOT NULL,
	[quantity] [int] NOT NULL,
	[order_id] [bigint] NULL,
	[subProduct_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Orders]    Script Date: 06/02/2021 12:47:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[order_date] [datetime] NULL,
	[payment_method] [nvarchar](255) NULL,
	[ship_address] [nvarchar](255) NULL,
	[status] [nvarchar](255) NULL,
	[total] [float] NULL,
	[customer_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Product]    Script Date: 06/02/2021 12:47:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[active] [bit] NOT NULL,
	[created_at] [datetime] NULL,
	[discount] [float] NULL,
	[long_description] [ntext] NULL,
	[marker] [nvarchar](50) NULL,
	[material] [nvarchar](255) NULL,
	[name] [nvarchar](255) NULL,
	[origin] [nvarchar](255) NULL,
	[price] [float] NULL,
	[short_description] [ntext] NULL,
	[tax] [float] NULL,
	[updated_at] [datetime] NULL,
	[views] [int] NULL,
	[category_id] [bigint] NULL,
	[supplier_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Roles]    Script Date: 06/02/2021 12:47:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Roles](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[role] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SubProduct]    Script Date: 06/02/2021 12:47:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SubProduct](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[color] [nvarchar](50) NULL,
	[created_at] [datetime] NULL,
	[inventory] [int] NULL,
	[name] [nvarchar](255) NULL,
	[size] [nvarchar](50) NULL,
	[updated_at] [datetime] NULL,
	[product_id] [bigint] NULL,
 CONSTRAINT [PK__SubProdu__3213E83FC534F504] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Supplier]    Script Date: 06/02/2021 12:47:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Supplier](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[address] [nvarchar](255) NULL,
	[email] [varchar](50) NULL,
	[name] [nvarchar](255) NOT NULL,
	[phone] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (1, 1, N'$2a$10$Gvp7EMf4EO6Z89tFJ70x.OHqoO.3mNvTV4GFHRX5RdXnc2rvw7wjq', N'customer1')
INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (2, 1, N'$2a$10$Gvp7EMf4EO6Z89tFJ70x.OHqoO.3mNvTV4GFHRX5RdXnc2rvw7wjq', N'customer2')
INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (3, 1, N'$2y$12$8QPhpyhF/g/6x61Dz5M1Herj/lnJ0o0YAsdwV8ffywx5sjzedjK3C', N'admin')
INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (4, 1, N'$2a$10$Gvp7EMf4EO6Z89tFJ70x.OHqoO.3mNvTV4GFHRX5RdXnc2rvw7wjq', N'tccuong')
INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (5, 1, N'$2a$10$Gvp7EMf4EO6Z89tFJ70x.OHqoO.3mNvTV4GFHRX5RdXnc2rvw7wjq', N'user123456')
INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (6, 1, N'$2a$10$Gvp7EMf4EO6Z89tFJ70x.OHqoO.3mNvTV4GFHRX5RdXnc2rvw7wjq', N'cuong0302')
INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (7, 1, N'$2a$10$FrT.Tb3DmtTD7/IKS7IFkeusiyHwjAeSMrkTd42fD/XG8KtJFrn5y', N'tccuongtest1')
INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (8, 1, N'$2a$10$yQSQ4nwbxuVClPaYFaKr0ubx57.96IS1/f2.7sR7Hf14UKvznS6AG', N'mkcuong')
INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (9, 1, N'$2a$10$TexktjbTyvpRkX1Amw51tOFKVMS.DoiLV3VJSvL6WnY9Es5HpFHO.', N'ccuong')
INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (10, 1, N'$2a$10$jIBnFsiV.u80SYv7/VsiJek0yIP2Qb8ynxrUITkDdSeB5Ks9APDwi', N'temail')
INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (11, 1, N'$2a$10$QOeyoMNmtR2IcJVthNYCS.4em95WhR0PHZhsHWMH.U1t4Xa2Ngn/a', N'temail2')
INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (12, 1, N'$2a$10$vzy37GHmU2a6p6.aOBvl.urbiScaruTq2ndkYakoGhgX8ndNGt8O6', N'tfinal1')
INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (13, 1, N'$2a$10$GU05XAv6bPJqgVtDkFvZfuaA27t7USZEFWnO3YbPXIWJwfMgwWa.a', N'ccuong123')
INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (14, 1, N'$2a$10$EGUcx22haoBRB8VUEqyIF.XjsLsn0ppKLa7afSh7heTU3mhZXTc52', N'cuongmaikien')
SET IDENTITY_INSERT [dbo].[Account] OFF
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (1, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (2, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (3, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (3, 2)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (4, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (5, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (6, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (7, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (8, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (9, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (10, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (11, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (12, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (13, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (14, 1)
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([id], [name]) VALUES (1, N'Quần Dài Nam')
INSERT [dbo].[Category] ([id], [name]) VALUES (2, N'Quần Dài Nữ')
INSERT [dbo].[Category] ([id], [name]) VALUES (3, N'Áo Thun Nam')
INSERT [dbo].[Category] ([id], [name]) VALUES (4, N'Áo Thun Nữ')
INSERT [dbo].[Category] ([id], [name]) VALUES (5, N'Áo Dài Tay Nam')
INSERT [dbo].[Category] ([id], [name]) VALUES (6, N'Áo Dài Tay Nữ')
INSERT [dbo].[Category] ([id], [name]) VALUES (7, N'Quần Short Nam')
INSERT [dbo].[Category] ([id], [name]) VALUES (8, N'Quần Short Nữ')
INSERT [dbo].[Category] ([id], [name]) VALUES (9, N'Áo Khoác Nam')
INSERT [dbo].[Category] ([id], [name]) VALUES (10, N'Áo Khoác Nữ')
INSERT [dbo].[Category] ([id], [name]) VALUES (11, N'Áo Len Nam')
INSERT [dbo].[Category] ([id], [name]) VALUES (12, N'Áo Len Nữ')
INSERT [dbo].[Category] ([id], [name]) VALUES (13, N'Váy Nữ')
INSERT [dbo].[Category] ([id], [name]) VALUES (22, N'Áo thun')
INSERT [dbo].[Category] ([id], [name]) VALUES (23, N'Quần thể thao')
INSERT [dbo].[Category] ([id], [name]) VALUES (27, N'Bodysuit')
INSERT [dbo].[Category] ([id], [name]) VALUES (28, N'Áo Sơ Mi Nam')
INSERT [dbo].[Category] ([id], [name]) VALUES (29, N'Đồ bộ')
SET IDENTITY_INSERT [dbo].[Category] OFF
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([id], [address], [email], [name], [phone], [account_id]) VALUES (1, N'15 Nguyễn Thái Sơn Quận Gò Vấp Thành Hồ Chí Minh', N'nguyenlananh15@gmail.com', N'Nguyễn Lan Anh', N'0961514852', 1)
INSERT [dbo].[Customer] ([id], [address], [email], [name], [phone], [account_id]) VALUES (2, N'20 Phan Văn Trị Quận Gò Vấp Thành Hồ Chí Minh', N'nguyenvanminh@gmail.com', N'Nguyễn Văn Minh', N'0976543672', 2)
INSERT [dbo].[Customer] ([id], [address], [email], [name], [phone], [account_id]) VALUES (3, N'131/1/2 Nơ Trang Long , Bình Thạnh , TP HCM', N'truongcongcuong0302@gmail.com', N'tccuong', N'0961940832', 4)
INSERT [dbo].[Customer] ([id], [address], [email], [name], [phone], [account_id]) VALUES (4, N'12 nguyễn văn bảo , phường 4 , gò vấp , TPHCM', N'sbmegass1@gmail.com', N'Truong Cong Cuong', N'0964946464', 5)
INSERT [dbo].[Customer] ([id], [address], [email], [name], [phone], [account_id]) VALUES (5, N'281/2/52 Bình Lợi ,P13,Bình Thạnh', N'sbmegass2@gmail.com', N'Cường', N'0961940833', 6)
INSERT [dbo].[Customer] ([id], [address], [email], [name], [phone], [account_id]) VALUES (6, N'Tân Phú , Tp HCM', N'sbmegass3@gmail.com', N'tccuongtest1', N'0961940833', 7)
INSERT [dbo].[Customer] ([id], [address], [email], [name], [phone], [account_id]) VALUES (7, N'Dương Quảng Hàm , Gò Vấp , TP HCM', N'mkcuong@gmail.com', N'mkcuong', N'0961940833', 8)
INSERT [dbo].[Customer] ([id], [address], [email], [name], [phone], [account_id]) VALUES (8, NULL, N'12cungchila123@gmail.com', N'Công Cường', N'0961940888', 9)
INSERT [dbo].[Customer] ([id], [address], [email], [name], [phone], [account_id]) VALUES (9, N'12 Nguyễn Văn Bảo , P4, Gò Vấp, TP HCM', N'truongcongcuong3222@gmail.com', N'temail', N'0961940999', 10)
INSERT [dbo].[Customer] ([id], [address], [email], [name], [phone], [account_id]) VALUES (10, N'Bình Thạnh', N'truongcongcuong322@gmail.com', N'temail2', N'0961999999', 11)
INSERT [dbo].[Customer] ([id], [address], [email], [name], [phone], [account_id]) VALUES (11, N'Gò Vấp', N'12cungchila312@gmail.com', N'tfinal1', N'0961299999', 12)
INSERT [dbo].[Customer] ([id], [address], [email], [name], [phone], [account_id]) VALUES (12, NULL, N'12cungchila12@gmail.com', N'ruong Cong Cuong', N'0961999888', 13)
INSERT [dbo].[Customer] ([id], [address], [email], [name], [phone], [account_id]) VALUES (13, N'Gò Vấp TP Hồ Chí Minh', N'maikiencuongiuh@gmail.com', N'Mai Kiên Cường', N'0961516941', 14)
SET IDENTITY_INSERT [dbo].[Customer] OFF
SET IDENTITY_INSERT [dbo].[ImagesUrl] ON 

INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (7, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fao-thun-tay-dai-co-lo-cho-be-theu-hinh-cuu.jpg?alt=media&token=76fda01d-43ff-4521-92d7-b37b2c76b8e0', 13)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (10, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fdam_cong_chua_khong_tay.jpg?alt=media&token=d70302b1-738b-4993-8840-f9112cfc3305', 16)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (11, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fquan-jean-be-gai-skinny-dai-lung-thun-wax-rach.jpg?alt=media&token=72d188de-d4fa-4407-8927-fb7bdb7c7969', 17)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (12, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fquan-the-thao-dai-lung-thun-mix-soc.jpg?alt=media&token=095b39f6-4041-4972-be5f-50da3a23f2de', 18)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (13, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fquan-jeans-moc-mau-tron-wash-nhe_2.jpg?alt=media&token=9dd19778-a155-4559-8946-9b8e7841bf83', 19)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (14, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fquan-short-the-thao-cho-be-trai-nang-dong.jpg?alt=media&token=ad91d6f5-53e5-4eea-bca6-6be41623d1d7', 20)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (15, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fao_thun_ba_lo_dinosaur.jpg?alt=media&token=12b4cb20-6241-49ad-a4d5-3ff18493d419', 11)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (21, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F3198-0_0512d9aa4734451a8a4c1e12361a7bff_master.png?alt=media&token=b548af90-0820-4066-97ec-c75fcb17417b', 25)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (24, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F9366-0_c97131dfe607472d847ec619ed72262a_master.png?alt=media&token=ea781f4f-ea44-4d46-89e5-d7504a1359ed', 27)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (26, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F_nik5241_masp90046__841893ea1dd24ab3a29564e668c11513_master.png?alt=media&token=390580c3-4695-4387-a392-5977aa3079f5', 29)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (29, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F30037_022a70eeb7834326bd264f9aa5d6185c_1024x1024.png?alt=media&token=ec1e998a-55c2-483e-b3ac-dd4d0bfecb53', 32)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (33, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F5549_a5e71efbaa4b419dbb529c17f713f7ac_master.png?alt=media&token=fa7977ae-6f76-4fde-996b-a997c89f7735', 33)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (34, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fdam-thun-be-gai-ngay-he-hoa-tiet-de-thuong.jpg?alt=media&token=b806fa7b-a23a-4bc4-bef1-d8572de6b121', 14)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (35, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fao_thun_day_mau_tron_3.jpg?alt=media&token=631acbed-8da9-472b-ad1c-3357bbcdce23', 10)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (36, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fao_thun_day_mau_tron_2.jpg?alt=media&token=ac17da29-7260-468f-994d-2e36269b0511', 10)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (37, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fao_thun_day_mau_tron_4.jpg?alt=media&token=a44cd7f3-3487-4f0f-b03c-261ffc50c3f8', 10)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (38, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fao_thun_day_mau_tron.jpg?alt=media&token=303d1ea0-8cd0-45c7-a785-2ce14b8e3124', 10)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (39, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F5517-x-0_40c286ef7b584250897bdf4dca78b336_master.png?alt=media&token=61f00b85-830a-4b51-b690-88f218f01e8d', 34)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (55, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F91056.png?alt=media&token=9094b95d-d58e-4ff6-ba9b-97cc10107770', 30)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (56, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F5555_18074c2c04ea4e0fa2e98f5ceab16122_1024x1024.png?alt=media&token=8cb64040-8ba1-465a-8b2e-105f4e896eda', 24)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (57, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fupload_1f5dbaa7bb3d4a708def575934588a88_1024x1024.png?alt=media&token=4f7c5a5e-cb70-4fa6-8fb8-40dc2be056dd', 36)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (61, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fao-thun-hoodie-co-non-be-gai_3.jpg?alt=media&token=8e396d7e-0839-4a1e-95b0-4b16bc3ec9db', 35)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (65, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fsetjeansmikeyden.jpg?alt=media&token=f1228aa8-1994-4a0d-9c3d-5371a61f0193', 37)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (66, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fdamsacmau1.jpg?alt=media&token=a921e9d5-f1b2-40e4-bc90-e2af20a5c229', 38)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (67, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fbosatnachkhunglongcam.jpg?alt=media&token=f5160fdb-ea6d-45ec-b047-c1f43a4fee71', 39)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (69, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2FAN423-77.jpg?alt=media&token=c3a51850-bc6c-45e7-989e-526cea5631e0', 41)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (70, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2FAN396-54.jpg?alt=media&token=81b52e8c-fc1f-40dd-90d0-16eceb57c3a3', 42)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (71, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fupload_a0edbc7dc4ce4d4ab485fc51b07a846e_1024x1024.png?alt=media&token=7254a9cc-e213-4202-a943-3867b79eaa10', 43)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (72, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F132.png?alt=media&token=b78d11da-c737-4567-8955-25d1bbadac92', 44)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (73, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F144.png?alt=media&token=b78f2607-49af-4591-aad1-207aea1c219c', 45)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (74, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F166.jpg?alt=media&token=3736dff9-2c41-46ff-b9c6-35efd4efe28c', 46)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (75, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fquanjean1.jpg?alt=media&token=63cdf812-d450-4ef2-a731-4fc89a04dd98', 47)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (76, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fbo-thun-mickey-deo-kinh-va-quan-ngo-cho-be-gai-p256896753b39-500x500.jpg?alt=media&token=5ce8e02d-dc3f-4b17-b9a5-d8b0c115f6e2', 48)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (77, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2FBACH5996-1-510x383.jpg?alt=media&token=1ddba1e9-f959-4d42-9694-5d3fb99d1d8c', 49)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (78, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fsetcroptopjoggerthethaobx0013.jpg?alt=media&token=3de3808b-4f18-4fc7-9413-7728d083d4c2', 50)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (80, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Ffe6e519b19b2b4704633720d58e57e88.jpg?alt=media&token=8914da05-1b28-4473-bd1a-864df2ced35d', 51)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (83, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fsuper.png?alt=media&token=5c28dd8d-9aee-47a9-98f3-f9786b12f4d7', 53)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (101, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fdo-bo-the-thao-cho-be-trai-nang-dong__4.jpg?alt=media&token=2327091b-cfe5-470c-ade2-40ea4a7d4348', 70)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (102, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F5252-0_9af9aaca04464f39993112420c6c7d15_1024x1024.png?alt=media&token=9d430a3f-6be4-45cc-b3f9-4c6a11a902df', 26)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (104, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fquan-be-trai-ngan-mrn-cf-b0321005-9-12m-xam.jpg?alt=media&token=8f1504b3-21af-43fa-8871-b89db0583c55', 71)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (107, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F5519-0_48f0fa7e89f24751a4ee98005241e710_1024x1024.jpg?alt=media&token=35974145-5ff1-418a-9259-48e28b23d0ca', 22)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (108, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F90172-0_901d936e694e4cb39708ac7fe5bb1c76_master.png?alt=media&token=29b84a82-1f6a-484b-8b46-406f70309eb6', 28)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (112, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F140152628.jpg?alt=media&token=7d2fffcc-f38e-445f-8850-3f184ddcfbfc', 40)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (113, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F5261-x-0_fcec175b4d0f490793bfc4db1720b0f0_1024x1024.png?alt=media&token=95f85b48-275d-41f8-82c7-b15e55497a74', 31)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (114, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F_nik7257_masp5529__1fe6a64da7744faf852b3d6fd8169872_1024x1024.png?alt=media&token=94392f56-1a69-4a41-ab5c-c4e0a68d8b52', 23)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (115, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fao-khoac-kaki-cho-be-trai-2-lop.jpg?alt=media&token=152c900c-f72f-4892-aee0-7adb07861904', 12)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (116, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2F90708-0_e0f4e00ccf3344cf9aaf9923459c0117_1024x1024.webp?alt=media&token=da849bb7-812d-4c4b-9f65-1e88ba852051', 21)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (117, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fkl.png?alt=media&token=d61ecf8f-d9a7-4fc4-a1fe-1b353f41234c', 69)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (118, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fvay_cong_chua.jpg?alt=media&token=3c90df59-c722-4930-9ce9-6ad570ea248f', 15)
INSERT [dbo].[ImagesUrl] ([id], [url], [product_id]) VALUES (119, N'https://firebasestorage.googleapis.com/v0/b/baby-clothes-shop.appspot.com/o/images%2Fbatman.png?alt=media&token=4ff9bb9e-1975-426f-b872-8cb3b2b966e1', 52)
SET IDENTITY_INSERT [dbo].[ImagesUrl] OFF
SET IDENTITY_INSERT [dbo].[OrderDetail] ON 

INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (31, 35000, 20, 7, 44)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (32, 35000, 19, 7, 45)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (33, 242100, 1, 8, 94)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (34, 250169.99999999997, 1, 8, 89)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (35, 75050, 1, 9, 48)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (36, 399000, 1, 9, 109)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (37, 175000, 1, 9, 148)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (38, 75050, 1, 10, 48)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (39, 250169.99999999997, 1, 11, 88)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (40, 199750, 1, 11, 80)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (41, 399000, 1, 12, 107)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (42, 149000, 1, 12, 117)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (43, 175000, 1, 13, 146)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (44, 129000, 1, 13, 100)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (45, 299250, 1, 13, 65)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (46, 189050, 1, 14, 105)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (47, 212500, 1, 14, 131)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (48, 149000, 1, 15, 92)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (49, 249000, 1, 15, 137)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (50, 149000, 1, 15, 116)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (51, 450000, 1, 16, 58)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (52, 195000, 1, 16, 70)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (53, 129000, 1, 16, 59)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (54, 189050, 1, 17, 103)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (55, 250169.99999999997, 1, 18, 87)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (56, 350000, 1, 18, 51)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (57, 94050, 1, 19, 202)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (58, 75050, 1, 20, 47)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (59, 350000, 3, 20, 50)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (60, 145000, 1, 20, 157)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (61, 85000, 1, 21, 55)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (62, 242100, 1, 21, 96)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (63, 425000, 1, 21, 179)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (64, 249000, 1, 22, 137)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (65, 339000, 2, 22, 124)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (66, 350000, 1, 23, 52)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (67, 175000, 1, 24, 147)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (68, 94050, 1, 25, 202)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (69, 280000, 1, 25, 164)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (70, 242100, 1, 26, 95)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (71, 193500, 1, 26, 196)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (72, 450000, 1, 27, 57)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (73, 35000, 1, 28, 46)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (74, 35000, 1, 28, 45)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (75, 209000, 1, 28, 114)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (76, 339000, 1, 29, 123)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (77, 85000, 1, 30, 55)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (78, 195000, 1, 30, 70)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (79, 350000, 1, 31, 51)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (80, 145000, 1, 32, 129)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (81, 280000, 1, 33, 163)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (82, 249000, 1, 34, 137)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (83, 75050, 1, 35, 49)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (84, 145000, 1, 35, 156)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (85, 145000, 1, 35, 155)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (86, 85000, 1, 36, 55)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (87, 35000, 1, 36, 45)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (88, 129000, 1, 36, 60)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (89, 199750, 1, 37, 81)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (90, 195000, 1, 37, 70)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (91, 119000, 1, 38, 291)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (92, 450000, 1, 39, 57)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (93, 189050, 1, 40, 105)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (94, 175000, 1, 41, 173)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (95, 99000, 1, 41, 193)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (96, 350000, 1, 42, 50)
SET IDENTITY_INSERT [dbo].[OrderDetail] OFF
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (7, CAST(0x0000AD2E010CF93E AS DateTime), N'COD', N'12 nguyễn văn bảo , phường 4 , gò vấp , TPHCM', N'COMPLETED', 1365000, 4)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (8, CAST(0x0000AD3700BF22F7 AS DateTime), N'COD', N'15 Nguyễn Thái Sơn Quận Gò Vấp Thành Hồ Chí Minh', N'COMPLETED', 492270, 1)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (9, CAST(0x0000AD3700BFCF74 AS DateTime), N'COD', N'Tân Phú , Tp HCM', N'COMPLETED', 649050, 6)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (10, CAST(0x0000AD3700C103EB AS DateTime), N'COD', N'Tân Phú , Tp HCM', N'COMPLETED', 75050, 6)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (11, CAST(0x0000AD3700C1C3A3 AS DateTime), N'COD', N'131/1/2 Nơ Trang Long , Bình Thạnh , TP HCM', N'CANCELED', 449920, 3)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (12, CAST(0x0000AD3700C381A2 AS DateTime), N'COD', N'12 nguyễn văn bảo , phường 4 , gò vấp , TPHCM', N'COMPLETED', 548000, 4)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (13, CAST(0x0000AD3700C5F37B AS DateTime), N'COD', N'Dương Quảng Hàm , Gò Vấp , TP HCM', N'COMPLETED', 603250, 7)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (14, CAST(0x0000AD3700C63D0D AS DateTime), N'COD', N'Dương Quảng Hàm , Gò Vấp , TP HCM', N'COMPLETED', 401550, 7)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (15, CAST(0x0000AD3700C6CBD8 AS DateTime), N'COD', N'20 Phan Văn Trị Quận Gò Vấp Thành Hồ Chí Minh', N'COMPLETED', 547000, 2)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (16, CAST(0x0000AD3700C70F16 AS DateTime), N'COD', N'20 Phan Văn Trị Quận Gò Vấp Thành Hồ Chí Minh', N'CANCELED', 774000, 2)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (17, CAST(0x0000AD3700C7E556 AS DateTime), N'COD', N'281/2/52 Bình Lợi ,P13,Bình Thạnh', N'COMPLETED', 189050, 5)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (18, CAST(0x0000AD37013AD668 AS DateTime), N'COD', N'15 Nguyễn Thái Sơn Quận Gò Vấp Thành Hồ Chí Minh', N'COMPLETED', 600170, 1)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (19, CAST(0x0000AD3800CB990D AS DateTime), N'COD', N'Dương Quảng Hàm , Gò Vấp , TP HCM', N'COMPLETED', 94050, 7)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (20, CAST(0x0000AD3800DBDBDB AS DateTime), N'COD', N'12 Nguyễn Văn Bảo , P4, Gò Vấp, TP HCM', N'CANCELED', 1270050, 9)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (21, CAST(0x0000AD3800DED357 AS DateTime), N'COD', N'Bình Thạnh', N'CANCELED', 752100, 10)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (22, CAST(0x0000AD3900A29A33 AS DateTime), N'COD', N'131/1/2 Nơ Trang Long , Bình Thạnh , TP HCM', N'COMPLETED', 927000, 3)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (23, CAST(0x0000AD3900AA1772 AS DateTime), N'COD', N'Gò Vấp', N'CANCELED', 350000, 11)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (24, CAST(0x0000AD3900B332D9 AS DateTime), N'COD', N'Bình Thạnh', N'COMPLETED', 175000, 10)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (25, CAST(0x0000AD3900B5C016 AS DateTime), N'COD', N'Bình Thạnh', N'COMPLETED', 374050, 10)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (26, CAST(0x0000AD3900B9A3EE AS DateTime), N'COD', N'12 nguyễn văn bảo , phường 4 , gò vấp , TPHCM', N'CANCELED', 435600, 4)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (27, CAST(0x0000AD3900C612D1 AS DateTime), N'COD', N'12 nguyễn văn bảo , phường 4 , gò vấp , TPHCM', N'COMPLETED', 450000, 4)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (28, CAST(0x0000AD3900C704CB AS DateTime), N'COD', N'20 Phan Văn Trị Quận Gò Vấp Thành Hồ Chí Minh', N'COMPLETED', 279000, 2)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (29, CAST(0x0000AD3900C711B3 AS DateTime), N'COD', N'20 Phan Văn Trị Quận Gò Vấp Thành Hồ Chí Minh', N'CANCELED', 339000, 2)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (30, CAST(0x0000AD3900CB3B50 AS DateTime), N'COD', N'Dương Quảng Hàm , Gò Vấp , TP HCM', N'PENDING', 280000, 7)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (31, CAST(0x0000AD3900CB45CA AS DateTime), N'COD', N'Dương Quảng Hàm , Gò Vấp , TP HCM', N'PENDING', 350000, 7)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (32, CAST(0x0000AD3900CB5CB8 AS DateTime), N'COD', N'15 Nguyễn Thái Sơn Quận Gò Vấp Thành Hồ Chí Minh', N'PENDING', 145000, 1)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (33, CAST(0x0000AD3900CB9285 AS DateTime), N'COD', N'20 Phan Văn Trị Quận Gò Vấp Thành Hồ Chí Minh', N'PENDING', 280000, 2)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (34, CAST(0x0000AD3900CBA877 AS DateTime), N'COD', N'131/1/2 Nơ Trang Long , Bình Thạnh , TP HCM', N'PENDING', 249000, 3)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (35, CAST(0x0000AD3900CBD896 AS DateTime), N'COD', N'281/2/52 Bình Lợi ,P13,Bình Thạnh', N'PENDING', 365050, 5)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (36, CAST(0x0000AD3900CC192A AS DateTime), N'COD', N'12 nguyễn văn bảo , phường 4 , gò vấp , TPHCM', N'PENDING', 249000, 4)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (37, CAST(0x0000AD3900CC56C9 AS DateTime), N'COD', N'Tân Phú , Tp HCM', N'PENDING', 394750, 6)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (38, CAST(0x0000AD3900CC6492 AS DateTime), N'COD', N'Tân Phú , Tp HCM', N'PENDING', 119000, 6)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (39, CAST(0x0000AD3900CC7A35 AS DateTime), N'COD', N'12 Nguyễn Văn Bảo , P4, Gò Vấp, TP HCM', N'PENDING', 450000, 9)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (40, CAST(0x0000AD3900CC9343 AS DateTime), N'COD', N'Bình Thạnh', N'PENDING', 189050, 10)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (41, CAST(0x0000AD3900CCBCD8 AS DateTime), N'COD', N'Gò Vấp', N'PENDING', 274000, 11)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (42, CAST(0x0000AD3B00D1C4ED AS DateTime), N'COD', N'Gò Vấp TP Hồ Chí Minh', N'PENDING', 350000, 13)
SET IDENTITY_INSERT [dbo].[Orders] OFF
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (10, 1, CAST(0x0000AD2100D339E8 AS DateTime), 0, N'áo cho bé gái ,....', N'HOT', N'cotton', N'Áo thun dây trơn', N'VietNam', 35000, N'chất liệu cotton thoáng mát ,...', 0.1, CAST(0x0000AD3701725A50 AS DateTime), 41, 4, 5)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (11, 1, CAST(0x0000AD2F00B5A86A AS DateTime), 0.05, N'Thiết kế thời trang, năng động cho bé tự do vận động
 Áo thun cotton cao cấp với tính năng siêu mền mịn, thấm hút mồ hôi đem lại sự thoáng mát và thoải mái cho bé 
', N'HOT', N'Cotton', N'Áo thun ba lỗ in hình khủng long', N'Vietnam', 79000, N'Áo thun ba lỗ dành cho bé trai in hình khủng long', 0.1, CAST(0x0000AD3701725A61 AS DateTime), 18, 3, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (12, 1, CAST(0x0000AD2F00B66918 AS DateTime), 0, N'Áo chất kaki, lót trong là lớp vải cotton mềm, bé mặc cảm giác mềm mại, rất thoải mái.
Áo rất dễ kết hợp, đa dạng với nhiều loại thời tiết.', N'HOT', N'Kaki', N'Áo khoác Kaki', N'Vietnam', 350000, N'Áo khoác Kaki 2 lớp cho bé', 0.2, CAST(0x0000AD3900CAA6D4 AS DateTime), 44, 9, 5)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (13, 1, CAST(0x0000AD2F00B7D78F AS DateTime), 0, N'Áo tay dài, cổ lọ, màu trơn, áo có bo tay, thêu hình chú cừu xinh xắn, thích hợp cho đi học, đi chơi, những ngày trở lạnh, chi tiết sản phẩm như hình chụp.', N'DEF', N'Thun borip', N'Áo thun dài tay in hình cừu', N'China', 85000, N'Áo thun tay dài cổ lọ cho bé gái, bé trai mặc ấm áp, dễ thương', 0.2, CAST(0x0000AD3700D014A7 AS DateTime), 11, 22, 4)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (14, 1, CAST(0x0000AD2F00B8E03A AS DateTime), 0, N'Đầm sát nách, bèo cổ, dáng xòe nhẹ, nhiều họa tiết xinh xắn.
Chất thun cotton mềm mịn mát mẻ đánh bay ngay cái nóng ngày hè.', N'DEF', N'Thun cotton', N'Đầm thun cho bé gái', N'Vietnam', 450000, N'đầm thun dành cho bé gái ngày hè họa tiết dễ thương.
Chất liệu cotton thoáng mát ,...', 0, CAST(0x0000AD3700C6D934 AS DateTime), 8, 13, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (15, 1, CAST(0x0000AD2F00C9DAF3 AS DateTime), 0, N'Vải gấm luôn là chất liệu mang đến vẻ đẹp sang trọng trong những bữa tiệc hoành trán, dáng đuôi cá, có voan bèo lai, lót 1 lớp kate mặt trong thích hợp  khi bé đi chơi.', N'DEF', N'gấm thun cao cấp', N'Váy công chúa', N'Vietnam', 329000, N'Áo tay ngắn, cổ tròn, đầm nhiều màu', 0.2, CAST(0x0000AD3900CCE3B2 AS DateTime), 5, 13, 2)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (16, 1, CAST(0x0000AD2F00CB55AF AS DateTime), 0.05, N'Đầm công chúa không tay trang trí hoa cho bé gái 3-7 tuổi', N'DIS', N'Cotton', N'Váy công chúa không tay', N'Vietnam', 315000, N'Đầm công chúa không tay trang trí hoa cho bé gái 3-7 tuổi', 0.1, CAST(0x0000AD3700C5BDD9 AS DateTime), 2, 13, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (17, 1, CAST(0x0000AD2F00CC34A9 AS DateTime), 0, N'Quần dài skinny lưng thun có túi, quần wax rách đắp da, siêu ngầu, quần có túi trước và sau, quần mặc ôm, dáng dài, chi tiết sản phẩm như hình chụp', N'DEF', N'Jean', N'Quần jean Skinny', N'China', 195000, N'Đầm công chúa không tay trang trí hoa cho bé gái 3-7 tuổi', 0.01, CAST(0x0000AD3700C6F3FC AS DateTime), 6, 2, 4)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (18, 1, CAST(0x0000AD2F00CDB5E2 AS DateTime), 0.1, N'Quần thể thao rất tiện chỉ cần phối với chiếc áo thun là bé có thể mặc đi chơi, mặc nhà hay mặc chơi thể thao với phong cách rất khỏe khoắn và năng động', N'DEF', N'thun', N'Quần thể thao kẻ sọc', N'Vietnam', 199000, N'Quần Thể Thao Cho Bé Dài Lưng Thun Mix Sọc Màu Trơn Năng Động (3 - 9 tuổi)', 0.01, CAST(0x0000AD3600B4B552 AS DateTime), 3, 23, 4)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (19, 1, CAST(0x0000AD2F00CE8769 AS DateTime), 0.15, N'Quần jeans dài trơn cho bé trai mặc cá tính.
Quần dài cho bé trai với chất vải jean co giãn nhẹ, có túi trước sau. Chất liệu co giãn giúp bé mặc thoải mái, dễ dàng điều chỉnh kích thước mà không gây ra khó chịu. ', N'DIS', N'jean', N'Quần jean mộc', N'Vietnam', 235000, N'Quần Jeans Mộc Dài Màu Trơn Đắp Rách Cho Bé Cực Ngầu (1 - 10 tuổi)', 0.01, CAST(0x0000AD3700C151BE AS DateTime), 4, 1, 4)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (20, 1, CAST(0x0000AD2F00CFE23B AS DateTime), 0.3, N'Quần short màu trơn, có dây rút, quần măc ôm vừa, lai quần xẻ nhẹ sành điệu. Kết hợp quần với áo thun hay áo kiểu cho bé mặc đi chơi vừa thoải mái lại rất thời trang. ', N'DIS', N'cotton', N'Quần sort thể thao', N'Vietnam', 75000, N'Quần Short Thể Thao Cho Bé Trai Năng Động Mùa Hè (3- 12 tuổi)', 0.01, CAST(0x0000AD3700BD1C8E AS DateTime), 2, 7, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (21, 1, CAST(0x0000AD35012B9583 AS DateTime), 0.05, N'Áo khoác gió trẻ em với thiết kế có mũ mang phong cách thời trang năng động. Với tính năng chống nhăn, chống nước tốt, cản gió,cản bụi, vừa giữ ấm nhưng vẫn thoáng khí mang đến cảm giác thoải mái khi vận động. Hướng dẫn sử dụng: Sản phẩm bền hơn khi giặt bằng tay và giặt máy ở chế độ nhẹ.', N'HOT', N'Cotton', N'Áo khoác gió YYYJQ96', N'Vietnam', 269000, N'Áo khoác gió trẻ em với thiết kế có mũ mang phong cách thời trang năng động ', 0.1, CAST(0x0000AD3900CAFEF8 AS DateTime), 64, 9, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (22, 0, CAST(0x0000AD35012CF31A AS DateTime), 0, N'- Sản phẩm thuộc bản quyền quốc tế Marvel - Disney
-Thiết kế thời trang,sản phẩm tập trung vào phong cách tự do, thoải mái 
- Áo thun bé trai cotton,khả năng thấm hút mồ hôi đem lại cảm giác an toàn và thoải mái cho bé  
- Hướng dẫn sử dụng: Sản phẩm bền hơn khi giặt bằng tay và giặt máy ở chế độ nhẹ  ', N'HOT', N'Cotton', N'Áo thun dài tay Spiderman', N'Vietnam', 149000, N'Áo thun dài tay Spider man Bé trai , Sản phẩm thuộc bản quyền quốc tế Marvel - Disney', 0.1, CAST(0x0000AD3900C75679 AS DateTime), 21, 5, 4)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (23, 1, CAST(0x0000AD35012E5360 AS DateTime), 0.05, N'Sản phẩm thuộc bản quyền quốc tế Disney. Áo khoác gió trẻ em với thiết kế có mũ mang phong cách thời trang năng động . Áo khoác bé gái với chất vải nỉ cao cấp với tính năng giữ ấm, thấm hút mồ hôi tốt và độ co dãn cao . Hướng dẫn sử dụng: Sản phẩm bền hơn khi giặt bằng tay và giặt máy ở chế độ nhẹ.', N'HOT', N'Vải Polyester', N'Áo khoác gió Elsa', N'Vietnam', 269000, N'Áo khoác  Elsa bé gái , Sản phẩm thuộc bản quyền quốc tế Disney', 0.1, CAST(0x0000AD3900CA948C AS DateTime), 36, 10, 4)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (24, 1, CAST(0x0000AD3501300233 AS DateTime), 0, N'Sản phẩm thuộc bản quyền Disney. Chất liệu cao cấp , thoáng mát , thoải mái .Hướng dẫn sử dụng: Sản phẩm bền hơn khi giặt bằng tay và giặt máy ở chế độ nhẹ.
', N'DEF', N'Cotton', N'Áo thun ngắn tay Minnie', N'China', 129000, N'Áo thun ngắn tay Minnie dành cho bé gái , sản phẩm thuộc bản quyền Disney', 0.1, CAST(0x0000AD3700C57D5E AS DateTime), 3, 4, 4)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (25, 1, CAST(0x0000AD35013216E2 AS DateTime), 0.05, N'Thiết kế thời trang, năng động và khỏe khoắn. Chất liệu chủ đạo là cotton cao cấp với tính năng siêu mềm mịn, khả năng thấm hút mồ hôi cao đem lại sự thoáng mát, thoải mái cho bé. Hướng dẫn sử dụng: Sản phẩm bền hơn khi giặt tay hoặc máy chế độ vừa', N'DIS', N'Cotton', N'Set body suit bé trai #1', N'Vietnam', 199000, N'Set bộ Body suit sơ sinh bé trai. Sản phẩm sản xuất tại Việt nam', 0.1, CAST(0x0000AD3700C7DE62 AS DateTime), 6, 27, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (26, 0, CAST(0x0000AD350136D2AA AS DateTime), 0, N'Sản phẩm thuộc bản quyền quốc tế Marvel - Disney. Thiết kế thời trang, năng động và khỏe khoắn. Chất liệu chủ đạo là cotton cao cấp với tính năng siêu mềm mịn, khả năng thấm hút mồ hôi cao đem lại sự thoáng mát, thoải mái cho bé. Hướng dẫn sử dụng: Sản phẩm bền hơn khi giặt tay hoặc máy chế độ vừa.', N'DEF', N'Cotton', N'Áo khoác nỉ Spider-man', N'Vietnam', 399000, N'Áo khoác nỉ Sprider-man dành cho bé trai. Sản phẩm thuộc bản quyền quốc tế Marvel - Disney.', 0.1, CAST(0x0000AD3900A08D80 AS DateTime), 8, 9, 4)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (27, 1, CAST(0x0000AD3501379D56 AS DateTime), 0, N'Thiết kế thời trang, năng động và khỏe khoắn. Chất liệu cotton được sản xuất từ sợi bông thiên nhiên với khả năng thấm hút mồ hôi cao đem lại sự thoáng mát, thoải mái cho bé. Hướng dẫn sử dụng: Sản phẩm bền hơn khi giặt tay hoặc máy chế độ vừa.', N'DEF', N'Cotton', N'Quần short jean #1', N'Vietnam', 199000, N'Quần short jean bé trai. Sản phẩm được sản xuất tại Việt Nam.', 0.1, CAST(0x0000AD3501379D56 AS DateTime), 0, 7, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (28, 1, CAST(0x0000AD3501393330 AS DateTime), 0, N' Thiết kế thời trang, sản phẩm tập trung phong cánh thoải mái và năng động. Áo sơ mi 100% cotton cao cấp với khả năng hấm hút mồ hôi vượt trội đem lại sự thoáng mát và thoải mái cho bé. Hướng dẫn sử dụng: Sản phẩm bền hơn khi giặt tay hoặc máy chế độ vừa.', N'HOT', N'Cotton', N'Áo sơ mi ngắn tay #1', N'China', 209000, N'Áo sơ mi ngắn tay bé trai. Sản phẩm được sản xuất tại Việt Nam.', 0.1, CAST(0x0000AD3900C8295C AS DateTime), 29, 28, 5)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (29, 1, CAST(0x0000AD35013A2339 AS DateTime), 0, N'Thiết kế thời trang, thoải mái. Chất liệu chủ đạo là cotton cao cấp với tính năng siêu mềm mịn, khả năng thấm hút mồ hôi cao đem lại sự thoáng mát, thoải mái cho bé. Hướng dẫn sử dụng: Sản phẩm bền hơn khi giặt tay hoặc máy chế độ vừa.', N'DEF', N'Cotton', N'Quần sort bé trai #2', N'Vietnam', 149000, N'Quần short dành cho bé trai. Hàng Việt Nam chất lượng cao.', 0.1, CAST(0x0000AD3700C6C47C AS DateTime), 6, 7, 5)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (30, 1, CAST(0x0000AD35013B10D0 AS DateTime), 0, N'Với thiết kế thời trang, năng động, áo ngắn tay cho bé là sự lựa chọn hoàn hảo giúp con tự do vận động. Áo thun cho bé làm từ cotton 100% siêu mền mịn, thấm hút mồ hôi đem lại sự thoáng mát và thoải mái. Hướng dẫn sử dụng: Sản phẩm bền hơn khi giặt bằng tay và giặt máy ở chế độ nhẹ.', N'DEF', N'Cotton', N'Áo thun ngắn tay cá Sấu', N'Vietnam', 89000, N'Áo thun ngắn tay in hình cá Sấu cho bé trai. Hàng Việt Nam chất lượng cao.', 0.1, CAST(0x0000AD3700D298A3 AS DateTime), 3, 3, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (31, 1, CAST(0x0000AD3501407F2D AS DateTime), 0, N'Áo khoác gió trẻ em với thiết kế có mũ mang phong cách thời trang năng động. Với tính năng cản gió, cản bui, vừa giữ ấm nhưng vẫn thoáng khí mang đến cảm giác thoải mái khi vận động . Hướng dẫn sử dụng: Sản phẩm bền hơn khi giặt bằng tay và giặt máy ở chế độ nhẹ.', N'HOT', N'Cotton', N'Áo khoác nỉ Captain America', N'Vietnam', 339000, N'Áo khoác nỉ Captain America dành cho bé trai. Hàng Việt Nam chất lượng cao.', 0.1, CAST(0x0000AD3900C94A45 AS DateTime), 16, 9, 4)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (32, 1, CAST(0x0000AD350141645B AS DateTime), 0, N'Thiết kế thời trang, năng động cho bé tự do vận động . Áo thun 100% cotton với tính năng siêu mền mịn, thấm hút mồ hôi đem lại sự thoáng mát và thoải mái cho bé . Hướng dẫn sử dụng: Sản phẩm bền hơn khi giặt bằng tay và giặt máy ở chế độ nhẹ.', N'DEF', N'Cotton', N'Áo thun ngắn tay T-Rex', N'Vietnam', 145000, N'Áo thun ngắn tay in hình  T-Rex dành cho bé trai.', 0.1, CAST(0x0000AD3700CFFD27 AS DateTime), 8, 3, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (33, 1, CAST(0x0000AD350143315A AS DateTime), 0.15, N'Thiết kế phong cách , thoải mái. Chất liệu cotton cao cấp thoáng mát , dễ hoạt động phù hợp cho bé trai mùa hè. Sản phẩm bền hơn khi giặt bằng tay và giặt máy ở chế độ nhẹ.', N'DIS', N'Cotton', N'Bộ thun Spider-man #1', N'Vietnam', 250000, N'Bộ thun ngắn tay Spider-man dành cho bé trai. Chát liệu cotton thoáng mát.', 0.1, CAST(0x0000AD3701725A69 AS DateTime), 8, 29, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (34, 1, CAST(0x0000AD350146F6D1 AS DateTime), 0, N'Sản phẩm thuộc bản quyền quốc tế Disney. Thiết kế thời trang, năng động cho bé tự do khám phá,vận động và phát triển trí tuệ toàn diện . Bộ ngắn tay cotton bé trai với khả năng thấm hút mồ hôi cao đem lại sự thoáng mát và thoải mái cho bé . Sản phẩm bền hơn khi giặt bằng tay và giặt máy ở chế độ nhẹ . Sản xuất tại Việt Nam.', N'DEF', N'Cotton', N'Bộ thun Spider-man #2', N'Vietnam', 249000, N'Bộ thun ngắn tay Spider-man #2 dành cho bé trai. Sản phẩm thuộc bản quyền quốc tế Disney.', 0.1, CAST(0x0000AD3700C6B5B9 AS DateTime), 6, 29, 4)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (35, 1, CAST(0x0000AD35014A1908 AS DateTime), 0.08, N'Thời trang và tiện lợi có thể thay thế áo khoác, áo thun hoodie bé gái tay dài, in hình cô gái chạy xe đạp, cầm dù dễ thương, áo co bo tay và bo áo, thích hợp cho đi học, đi chơi những ngày trở lạnh. Chất thun cotton mềm mại, mặc mát. Màu trắng, vàng và hồng tươi tắn. Size 4 - 12 tuổi, mẹ mua cho con mặc năng động, đáng yêu.', N'DEF', N'Thun Cotton', N'Áo thun Hoddie #1', N'Vietnam', 149000, N'Áo Thun Hoodie Có Nón Bé Gái Tay Dài In Hình Cô Gái Cầm Dù Xinh Xắn.', 0.1, CAST(0x0000AD37013AAE15 AS DateTime), 7, 6, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (36, 1, CAST(0x0000AD35014C9648 AS DateTime), 0, N'Đầm bé gái được thiết kế dễ thương, chất liệu nhẹ nhàng, thoải mái, thích hợp cho bé đi chơi, đi học hoặc ở nhà đều được. Sản phẩm bền hơn khi giặt bằng tay và giặt máy ở chế độ nhẹ.', N'DEF', N'Cotton', N'Đầm bé gái #1', N'Vietnam', 175000, N'Đầm dành bé gái ngày hè. Thiết kế đơn giản , chất liệu thoáng mát.', 0.1, CAST(0x0000AD3701725A65 AS DateTime), 16, 13, 5)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (37, 1, CAST(0x0000AD3700CAA761 AS DateTime), 0, N'Chào set jean lửng cho bé gái diện ngày hè năng động. Áo chất cotton 4c mềm mịn, thiết kế 3 lỗ năng động. Quần jean lửng, chất jean thun co giãn thoải mái, in hình mickey sắc nét bao bong tróc. Nhí size 2-9.', N'DEF', N'Thun cotton, Jeans', N'Bộ Jeans Lửng Mickey', N'Vietnam', 185000, N'Set jeans lửng Mickey dễ thương cho bé gái 2 - 9 Tuổi', 0.1, CAST(0x0000AD3700CAA761 AS DateTime), 0, 29, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (38, 1, CAST(0x0000AD3700CBEB34 AS DateTime), 0, N'Đầm sọc sắc màu  siêu hot cho bé gái. Chất thun cotton in thấm cực đẹp, may kiểu phía sau. Chất liệu thoáng mát , thoải mái.', N'DEF', N'Cotton', N'Đầm sọc nhiều màu', N'Vietnam', 145000, N'Đầm sọc nhiều màu  dễ thương cho bé gái 2 - 9 tuổi', 0.1, CAST(0x0000AD3700CBEB34 AS DateTime), 8, 13, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (39, 1, CAST(0x0000AD3700CECAD1 AS DateTime), 0.1, N'Bộ thun sát nách sọc khủng long cho bé trai ngày hè. Áo may thun cotton sọc dệt 4c mềm mịn, hình in giả nhung đắp đuôi khủng long. Quần chất thun cotton 4c mềm mịn, 2 bên đắp vây.', N'DIS', N'Cotton', N'Set đồ khủng long #1', N'Vietnam', 125000, N'Bộ sát nách sọc khủng long dễ thương cho bé trai từ 2 - 9 tuổi.', 0.1, CAST(0x0000AD3700DC775C AS DateTime), 2, 29, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (40, 1, CAST(0x0000AD3700D15375 AS DateTime), 0, N'Thiết kế thời trang,sản phẩm tập trung vào phong cách tự do, thoải mái - Áo thun bé trai cotton,khả năng thấm hút mồ hôi đem lại cảm giác an toàn và thoải mái cho bé. Sản phẩm được nhập khẩu từ Mỹ.', N'DEF', N'Cotton', N'Áo thun Ant-man', N'USA', 280000, N'Áo thun in hình siêu anh hùng Ant-man dành cho bé trai.', 0.1, CAST(0x0000AD3900C944EE AS DateTime), 12, 3, 4)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (41, 1, CAST(0x0000AD3700D2FA0C AS DateTime), 0, N'Áo bé trai thun in hình Cá Sấu. Chất vải thun cotton xịn, mỏng, mềm mịn, mát r­ượi. Màu sắc đẹp, hình in rất mỹ thuật, tuyệt đối không tróc, dính. Loại áo này bé mặc quanh năm không bao giờ chán, vải bền đẹp, mặc ở nhà, đi chơi, đi học đều hợp.', N'DEF', N'Cotton', N'Áo thun in hình Cá Sấu', N'Vietnam', 99000, N'Áo thun in hình Cá Sấu cho bé trai.', 0.1, CAST(0x0000AD3700D2FA0C AS DateTime), 0, 3, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (42, 1, CAST(0x0000AD3700D76E48 AS DateTime), 0.05, N'Áo bé gái hàng VN xuất xịn, đẹp chuẩn chỉnh từ chất liệu, phom dáng, màu saắc đến họa tiết trên áo. Chất vải thun cotton rất đẹp, mềm mịn, độ co giãn cao, thấm hút mồ hôi giúp da luôn thoáng mát, bền vải. Màu sắc tươi sáng, trẻ trung, hình in xịn và cực đẹp.', N'DIS', N'Cotton', N'Áo thun in loang', N'Vietnam', 135000, N'Áo bé gái chất thun cotton đẹp xuất sắc hàng  chất lượng cao ,màu xanh in loang', 0.1, CAST(0x0000AD3700D76E48 AS DateTime), 2, 4, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (43, 1, CAST(0x0000AD3700D8A913 AS DateTime), 0, N'Áo thun in hình mèo sassy dễ thương cho bé gái, thun vải cotton thoáng mát, hút mồ hôi tốt, có thể kết hợp cùng quần jean thành bộ đi chơi.', N'DEF', N'Cotton', N'Áo thun Mèo Sasy', N'Vietnam', 175000, N'Áo thun in hình mèo Sassy dễ thương cho bé gái.', 0.1, CAST(0x0000AD3700D8A913 AS DateTime), 2, 4, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (44, 1, CAST(0x0000AD3700D968DD AS DateTime), 0, N'Đầm trắng bé gái cao cấp được thiết kế dễ thương, chất liệu ren cao cấp, có lót trong, thích hợp cho bé đi chơi, hoặc dự tiệc. Thiết kế khoét sau thời trang.', N'DEF', N'Polyester Interlock', N'Đầm ren trắng', N'Vietnam', 399000, N'Đầm ren trắng bé gái 2 lớp kèm dây nịt. Thiết kế không tay.', 0.1, CAST(0x0000AD3700DE985D AS DateTime), 2, 13, 5)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (45, 1, CAST(0x0000AD3700DA2F40 AS DateTime), 0, N'Đầm bé gái cao cấp được thiết kế dễ thương, chất liệu cao cấp, thích hợp cho bé đi chơi, hoặc dự tiệc.', N'DEF', N' Polyester Mesh', N'Đầm ren trắng cao cấp', N'USA', 425000, N'Đầm bé gái ren trắng 2 lớp, không tay  cao cấp.', 0.15, CAST(0x0000AD3700DA2F40 AS DateTime), 2, 13, 5)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (46, 1, CAST(0x0000AD3700DB22CC AS DateTime), 0, N'Thiết kế thời trang,sản phẩm tập trung vào phong cách tự do, thoải mái - Áo thun bé trai cotton,khả năng thấm hút mồ hôi đem lại cảm giác an toàn và thoải mái cho bé.', N'DEF', N'Cotton', N'Áo thun GNARLY', N'USA', 250000, N'Áo thun in chữ  GNARLY , hàng nhập khẩu từ Mỹ. ', 0.15, CAST(0x0000AD3700DB22CC AS DateTime), 0, 3, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (47, 1, CAST(0x0000AD3700DBC3B0 AS DateTime), 0, N'Quần jeans wash cực bụi cho bé size lớn. Chất jeans mềm co giãn, may tăng đơ, wash màu cực bụi dành cho bé lớn.', N'DEF', N'jeans', N'Quần Jeans dài #1', N'Vietnam', 199000, N'Quần jeasn dài Size cồ dễ thương cho bé trai.', 0.1, CAST(0x0000AD3700DBC3B0 AS DateTime), 0, 1, 5)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (48, 1, CAST(0x0000AD3700DCB684 AS DateTime), 0, N'Bộ thun MICKEY đeo kính và quần ngố cho bé gái. Chất liệu cotton thoải mái , thoáng mát . Thiết kế thời trang. ', N'DEF', N'Cotton', N'Bộ thun Mickey #1', N'Vietnam', 135000, N'Bộ thun MICKEY đeo kính và quần ngố cho bé gái.', 0.1, CAST(0x0000AD3700DCB684 AS DateTime), 0, 29, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (49, 1, CAST(0x0000AD3700DD7EAC AS DateTime), 0, N'Bộ ba lỗ bé trai hoạ tiết con ong Chất thun cotton 4 chiều dày dặn, sờ rất mát tay. Màu sắc bắt mắt với hoạ tiết chú ong đáng yêu. Quần phối màu tông xuyệt tông với hoạ tiết áo nhìn rất cá tính. Phom ôm.', N'DEF', N'Cotton', N'Bộ thun mùa hè', N'Vietnam', 99000, N'Bộ bé trai 3 lỗ mùa hè in hình ong.', 0.1, CAST(0x0000AD3700DD7EAC AS DateTime), 2, 29, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (50, 1, CAST(0x0000AD3700DE5EEB AS DateTime), 0.1, N'Set áo croptop quần jogger phong cách thể thao năng động cho bé gái đi chơi, dạo phố. Nguyên set chất thun cotton mềm mát, thấm hút mồ hôi. Áo in hình sắc nét, form croptop. Quần jogger đậm chất thể thao. ', N'DIS', N'Cotton', N'Set đồ cho bé gái', N'Vietnam', 215000, N'Set áo croptop quần jogger thể thao dễ thương cho bé gái.', 0.1, CAST(0x0000AD3700DE5EEB AS DateTime), 2, 29, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (51, 0, CAST(0x0000AD37013D31C6 AS DateTime), 0, N'Chất vải thun 100% cotton mềm mịn, co giãn 4 chiều sẽ là gợi ý thú vị cho mẹ và bé trong mùa hè năm nay. Là sự kết hợp hoàn hảo của 2 chất liệu cotton và spandex, bộ quần áo không chỉ dễ dàng thấm hút mồ hôi, có độ bền cao mà còn thân thiện với làn da mỏng manh của bé. Nổi bật với kiểu dáng dễ thương cùng họa tiết hình thú ngộ nghĩnh, bộ quần áo chắc hẳn sẽ làm các bé trai thích thú, tạo nên nét dễ thương mà không kém phần tinh nghịch.', N'DEF', N'Cotton', N'Bộ đồ trẻ em Zebra', N'Vietnam', 129000, N'Bộ quần áo trẻ em dành cho  bé trai . Mẫu Ngựa Cam cao cấp .', 0.1, CAST(0x0000AD37015A835F AS DateTime), 3, 29, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (52, 1, CAST(0x0000AD37015B3EB7 AS DateTime), 0.05, N'100% thương hiệu mới, chất lượng cao. Vải cotton mềm và thoáng khí. Thoải mái khi chạm và mặc. Chất liệu: Cotton Blend', N'DIS', N'Cotton', N'Áo thun Batman', N'Vietnam', 99000, N'Áo thun in hình biểu tượng Batman dành cho bé trai.', 0.1, CAST(0x0000AD3900CD06C6 AS DateTime), 10, 3, 4)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (53, 0, CAST(0x0000AD37015C6146 AS DateTime), 0, N'100% thương hiệu mới, chất lượng cao. Áo thun in hình biểu tượng Super-Man dành cho bé trai. Vải cotton mềm và thoáng khí. Thoải mái khi chạm và mặc. Chất liệu: Cotton Blend.', N'DEF', N'Cotton', N'Áo thun Super-Man', N'Vietnam', 99000, N'Áo thun in hình biểu tượng Super-Man dành cho bé trai.', 0.1, CAST(0x0000AD37015D3B0B AS DateTime), 1, 3, 4)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (69, 1, CAST(0x0000AD37017A09E8 AS DateTime), 0, N'Áo thun trẻ em  ngắn tay bé trai in hình Khủng long gai màu xanh. Sản phẩm được làm từ vải cotton 100% giúp bé thoải mải vận động và thấm hút mồ hơi cực tốt.', N'DEF', N'Cotton', N'Áo thun Khủng long #2', N'Vietnam', 75000, N'Áo thun in hình khủng long cho bé trai mẫu #2.', 0.1, CAST(0x0000AD3900CCD1A5 AS DateTime), 4, 3, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (70, 1, CAST(0x0000AD3800C64B85 AS DateTime), 0, N'Đồ bộ thể thao cho bé trai mặc đi chơi thoải mái, năng động. Mùa hè khỏe khoắn và năng động với bộ thể thao áo sát nách, in chữ, quần lửng ôm vừa, quần lưng thun (mặc ngang gối). Chất thun thể thao mềm mát, bé mặc thoải mái, dễ chịu lúc đi chơi, đi dã ngoại hay chơi thể thao. Màu thể thao vàng, xanh và đỏ nổi bật.', N'DEF', N'Cotton', N'Bộ thể thao #1', N'Vietnam', 119000, N'Đồ Bộ Thể Thao Cho Bé Trai Năng Động Sát Nách phù hợp từ  7 - 15 tuổi.', 0.1, CAST(0x0000AD3800C64B85 AS DateTime), 2, 29, 3)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (71, 1, CAST(0x0000AD3900C510ED AS DateTime), 0, N'Quần thun bé trai ngắn, nằm trong bộ sưu tập bạn Gấu. Thương hiệu CF, thiết kế và sản xuất tại Việt Nam. Họa tiết bé Gấu mạnh mẽ hợp thời trang .Dành cho bé từ 6 đến 24 tháng.', N'DEF', N'Cotton', N'Quần ngắn trẻ em #1', N'Vietnam', 109000, N'Quần ngắn mẫu #1 dành cho bé trai , phù hợp từ 6-24 tháng.', 0.1, CAST(0x0000AD3900C510ED AS DateTime), 0, 7, 3)
SET IDENTITY_INSERT [dbo].[Product] OFF
SET IDENTITY_INSERT [dbo].[Roles] ON 

INSERT [dbo].[Roles] ([id], [role]) VALUES (1, N'ROLE_CUSTOMER')
INSERT [dbo].[Roles] ([id], [role]) VALUES (2, N'ROLE_ADMIN')
SET IDENTITY_INSERT [dbo].[Roles] OFF
SET IDENTITY_INSERT [dbo].[SubProduct] ON 

INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (43, N'Trắng', CAST(0x0000AD2100D339EC AS DateTime), 15, N'Áo thun dây trơn Trắng X', N'3', CAST(0x0000AD3501459018 AS DateTime), 10)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (44, N'Đen', CAST(0x0000AD2100D339ED AS DateTime), 3, N'Áo thun dây trơn Đen X', N'3', CAST(0x0000AD3501459018 AS DateTime), 10)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (45, N'Vàng', CAST(0x0000AD2100D339EE AS DateTime), 14, N'Áo thun dây trơn Vàng L', N'5', CAST(0x0000AD3900CC192B AS DateTime), 10)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (46, N'Hồng', CAST(0x0000AD2100D339EE AS DateTime), 4, N'Áo thun dây trơn Hồng X', N'7', CAST(0x0000AD3900C704CC AS DateTime), 10)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (47, N'Đỏ', CAST(0x0000AD2F00B5A86E AS DateTime), 19, N'Áo thun ba lỗ in hình khủng long Đỏ 1', N'1', CAST(0x0000AD3800DBDBDD AS DateTime), 11)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (48, N'Xanh', CAST(0x0000AD2F00B5A86F AS DateTime), 3, N'Áo thun ba lỗ in hình khủng long Xanh 1', N'1', CAST(0x0000AD3700C103EB AS DateTime), 11)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (49, N'Đỏ', CAST(0x0000AD2F00B5A86F AS DateTime), 9, N'Áo thun ba lỗ in hình khủng long Đỏ 2', N'2', CAST(0x0000AD3900CBD896 AS DateTime), 11)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (50, N'Cam', CAST(0x0000AD2F00B6691A AS DateTime), 51, N'Áo khoác Kaki Cam 2', N'2', CAST(0x0000AD3B00D1C4F1 AS DateTime), 12)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (51, N'Vàng', CAST(0x0000AD2F00B6691A AS DateTime), 38, N'Áo khoác Kaki Vàng 2', N'2', CAST(0x0000AD3900CB45CB AS DateTime), 12)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (52, N'Cam', CAST(0x0000AD2F00B6691A AS DateTime), 29, N'Áo khoác Kaki Cam 3', N'3', CAST(0x0000AD3900CAA6D8 AS DateTime), 12)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (53, N'Vàng', CAST(0x0000AD2F00B7D790 AS DateTime), 40, N'Áo thun dài tay in hình cừu Vàng 5', N'5', CAST(0x0000AD2F00B7D790 AS DateTime), 13)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (54, N'Xanh', CAST(0x0000AD2F00B7D790 AS DateTime), 30, N'Áo thun dài tay in hình cừu Xanh 5', N'5', CAST(0x0000AD2F00B7D790 AS DateTime), 13)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (55, N'Vàng', CAST(0x0000AD2F00B7D790 AS DateTime), 17, N'Áo thun dài tay in hình cừu Vàng 6', N'6', CAST(0x0000AD3900CC192B AS DateTime), 13)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (56, N'Trắng', CAST(0x0000AD2F00B8E03B AS DateTime), 50, N'Đầm thun cho bé gái Trắng 6', N'6', CAST(0x0000AD350144DBEE AS DateTime), 14)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (57, N'Trắng', CAST(0x0000AD2F00B8E03C AS DateTime), 78, N'Đầm thun cho bé gái Trắng 8', N'8', CAST(0x0000AD3900CC7A35 AS DateTime), 14)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (58, N'Trắng', CAST(0x0000AD2F00B8E03C AS DateTime), 49, N'Đầm thun cho bé gái Trắng 10', N'10', CAST(0x0000AD3700C70F16 AS DateTime), 14)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (59, N'Trắng', CAST(0x0000AD2F00C9DAF5 AS DateTime), 29, N'Váy công chúa Trắng 3', N'3', CAST(0x0000AD3900CCE3B6 AS DateTime), 15)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (60, N'Trắng', CAST(0x0000AD2F00C9DAF6 AS DateTime), 29, N'Váy công chúa Trắng 4', N'4', CAST(0x0000AD3900CCE3B6 AS DateTime), 15)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (61, N'Trắng', CAST(0x0000AD2F00C9DAF6 AS DateTime), 20, N'Váy công chúa Trắng 5', N'5', CAST(0x0000AD3900CCE3B6 AS DateTime), 15)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (62, N'Hồng', CAST(0x0000AD2F00CB55B0 AS DateTime), 30, N'Váy công chúa không tay Hồng 1', N'1', CAST(0x0000AD2F00CB55B0 AS DateTime), 16)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (63, N'Trắng', CAST(0x0000AD2F00CB55B1 AS DateTime), 20, N'Váy công chúa không tay Trắng 1', N'1', CAST(0x0000AD2F00CB55B1 AS DateTime), 16)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (64, N'Hồng', CAST(0x0000AD2F00CB55B1 AS DateTime), 20, N'Váy công chúa không tay Hồng 2', N'2', CAST(0x0000AD2F00CB55B1 AS DateTime), 16)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (65, N'Trắng', CAST(0x0000AD2F00CB55B1 AS DateTime), 14, N'Váy công chúa không tay Trắng 2', N'2', CAST(0x0000AD3700C5F37B AS DateTime), 16)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (66, N'Xám', CAST(0x0000AD2F00CC34AA AS DateTime), 25, N'Quần jean Skinny Xám 3', N'3', CAST(0x0000AD2F00CC34AA AS DateTime), 17)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (67, N'Xanh', CAST(0x0000AD2F00CC34AA AS DateTime), 30, N'Quần jean Skinny Xanh 3', N'3', CAST(0x0000AD2F00CC34AA AS DateTime), 17)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (68, N'Xám', CAST(0x0000AD2F00CC34AB AS DateTime), 20, N'Quần jean Skinny Xám 5', N'5', CAST(0x0000AD2F00CC34AB AS DateTime), 17)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (69, N'Xám', CAST(0x0000AD2F00CC34AB AS DateTime), 15, N'Quần jean Skinny Xám 7', N'7', CAST(0x0000AD2F00CC34AB AS DateTime), 17)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (70, N'Xanh', CAST(0x0000AD2F00CC34AC AS DateTime), 7, N'Quần jean Skinny Xanh 11', N'11', CAST(0x0000AD3900CC56CA AS DateTime), 17)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (71, N'Đen', CAST(0x0000AD2F00CDB5E4 AS DateTime), 15, N'Quần thể thao kẻ sọc Đen 3', N'3', CAST(0x0000AD2F00CDB5E4 AS DateTime), 18)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (72, N'Đỏ', CAST(0x0000AD2F00CDB5E4 AS DateTime), 30, N'Quần thể thao kẻ sọc Đỏ 3', N'3', CAST(0x0000AD2F00CDB5E4 AS DateTime), 18)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (73, N'Xanh', CAST(0x0000AD2F00CDB5E4 AS DateTime), 30, N'Quần thể thao kẻ sọc Xanh 3', N'3', CAST(0x0000AD2F00CDB5E4 AS DateTime), 18)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (74, N'Đen', CAST(0x0000AD2F00CDB5E4 AS DateTime), 19, N'Quần thể thao kẻ sọc Đen 5', N'5', CAST(0x0000AD2F00CDB5E4 AS DateTime), 18)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (75, N'Xanh', CAST(0x0000AD2F00CDB5E5 AS DateTime), 11, N'Quần thể thao kẻ sọc Xanh 7', N'7', CAST(0x0000AD2F00CDB5E5 AS DateTime), 18)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (76, N'Trắng', CAST(0x0000AD2F00CE8769 AS DateTime), 15, N'Quần jean mộc Trắng 1', N'1', CAST(0x0000AD2F00CE8769 AS DateTime), 19)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (77, N'Đen', CAST(0x0000AD2F00CE876A AS DateTime), 20, N'Quần jean mộc Đen 3', N'3', CAST(0x0000AD2F00CE876A AS DateTime), 19)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (78, N'Trắng', CAST(0x0000AD2F00CE876A AS DateTime), 15, N'Quần jean mộc Trắng 3', N'3', CAST(0x0000AD2F00CE876A AS DateTime), 19)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (79, N'Trắng', CAST(0x0000AD2F00CE876A AS DateTime), 13, N'Quần jean mộc Trắng 5', N'5', CAST(0x0000AD2F00CE876A AS DateTime), 19)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (80, N'Đen', CAST(0x0000AD2F00CE876B AS DateTime), 13, N'Quần jean mộc Đen 7', N'7', CAST(0x0000AD3700C1C3A4 AS DateTime), 19)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (81, N'Trắng', CAST(0x0000AD2F00CE876B AS DateTime), 16, N'Quần jean mộc Trắng 7', N'7', CAST(0x0000AD3900CC56CA AS DateTime), 19)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (82, N'Xanh', CAST(0x0000AD2F00CFE23D AS DateTime), 15, N'Quần sort thể thao Xanh 1', N'1', CAST(0x0000AD2F00CFE23D AS DateTime), 20)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (83, N'Đen', CAST(0x0000AD2F00CFE23D AS DateTime), 10, N'Quần sort thể thao Đen 1', N'1', CAST(0x0000AD2F00CFE23D AS DateTime), 20)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (84, N'Xanh', CAST(0x0000AD2F00CFE23E AS DateTime), 11, N'Quần sort thể thao Xanh 3', N'3', CAST(0x0000AD2F00CFE23E AS DateTime), 20)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (85, N'Đen', CAST(0x0000AD2F00CFE23E AS DateTime), 10, N'Quần sort thể thao Đen 5', N'5', CAST(0x0000AD2F00CFE23E AS DateTime), 20)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (86, N'Xám', CAST(0x0000AD35012B9587 AS DateTime), 5, N'Áo khoác gió YYYJQ96 Xám 6', N'6', CAST(0x0000AD3900CAFEFC AS DateTime), 21)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (87, N'Đỏ', CAST(0x0000AD35012B9588 AS DateTime), 6, N'Áo khoác gió YYYJQ96 Đỏ 6', N'6', CAST(0x0000AD3900CAFEFC AS DateTime), 21)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (88, N'Xám', CAST(0x0000AD35012B9588 AS DateTime), 2, N'Áo khoác gió YYYJQ96 Xám 7', N'7', CAST(0x0000AD3900CAFEFC AS DateTime), 21)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (89, N'Đỏ', CAST(0x0000AD35012B9589 AS DateTime), 3, N'Áo khoác gió YYYJQ96 Đỏ 8', N'8', CAST(0x0000AD3900CAFEFC AS DateTime), 21)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (90, N'Đen', CAST(0x0000AD35012CF31B AS DateTime), 25, N'Áo thun dài tay Spiderman Đen 5', N'5', CAST(0x0000AD3900C7567D AS DateTime), 22)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (91, N'Đỏ', CAST(0x0000AD35012CF31C AS DateTime), 5, N'Áo thun dài tay Spiderman Đỏ 5', N'5', CAST(0x0000AD3900C7567D AS DateTime), 22)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (92, N'Đen', CAST(0x0000AD35012CF31C AS DateTime), 1, N'Áo thun dài tay Spiderman Đen 6', N'6', CAST(0x0000AD3900C7567D AS DateTime), 22)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (93, N'Đỏ', CAST(0x0000AD35012CF31C AS DateTime), 3, N'Áo thun dài tay Spiderman Đỏ 8', N'8', CAST(0x0000AD3900C7567D AS DateTime), 22)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (94, N'Xanh', CAST(0x0000AD35012E5360 AS DateTime), 6, N'Áo khoác gió Elsa Xanh 5', N'5', CAST(0x0000AD3900CA9496 AS DateTime), 23)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (95, N'Hồng', CAST(0x0000AD35012E5361 AS DateTime), 8, N'Áo khoác gió Elsa Hồng 5', N'5', CAST(0x0000AD3900CA9496 AS DateTime), 23)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (96, N'Xanh', CAST(0x0000AD35012E5361 AS DateTime), 3, N'Áo khoác gió Elsa Xanh 7', N'7', CAST(0x0000AD3900CA9496 AS DateTime), 23)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (97, N'Xanh', CAST(0x0000AD35012E5361 AS DateTime), 1, N'Áo khoác gió Elsa Xanh 9', N'9', CAST(0x0000AD3900CA9496 AS DateTime), 23)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (98, N'Vàng', CAST(0x0000AD3501300233 AS DateTime), 11, N'Áo thun ngắn tay Minnie Vàng 5', N'5', CAST(0x0000AD3600AAAAC5 AS DateTime), 24)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (99, N'Hồng', CAST(0x0000AD3501300233 AS DateTime), 7, N'Áo thun ngắn tay Minnie Hồng 5', N'5', CAST(0x0000AD3600AAAAC5 AS DateTime), 24)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (100, N'Vàng', CAST(0x0000AD3501300234 AS DateTime), 5, N'Áo thun ngắn tay Minnie Vàng 7', N'7', CAST(0x0000AD3700C5F37B AS DateTime), 24)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (101, N'Hồng', CAST(0x0000AD3501300234 AS DateTime), 3, N'Áo thun ngắn tay Minnie Hồng 9', N'9', CAST(0x0000AD3600AAAAC5 AS DateTime), 24)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (102, N'Xám', CAST(0x0000AD35013216E3 AS DateTime), 15, N'Set body suit bé trai #1 Xám 1', N'1', CAST(0x0000AD35013216E3 AS DateTime), 25)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (103, N'Trắng', CAST(0x0000AD35013216E3 AS DateTime), 6, N'Set body suit bé trai #1 Trắng 1', N'1', CAST(0x0000AD3700C7E557 AS DateTime), 25)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (104, N'Xám', CAST(0x0000AD35013216E3 AS DateTime), 7, N'Set body suit bé trai #1 Xám 2', N'2', CAST(0x0000AD35013216E3 AS DateTime), 25)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (105, N'Trắng', CAST(0x0000AD35013216E3 AS DateTime), 11, N'Set body suit bé trai #1 Trắng 2', N'2', CAST(0x0000AD3900CC9343 AS DateTime), 25)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (106, N'Xám', CAST(0x0000AD350136D2AB AS DateTime), 7, N'Áo khoác nỉ Spider-man Xám 6', N'6', CAST(0x0000AD3900A08D8A AS DateTime), 26)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (107, N'Đỏ', CAST(0x0000AD350136D2AB AS DateTime), 3, N'Áo khoác nỉ Spider-man Đỏ 6', N'6', CAST(0x0000AD3900A08D8A AS DateTime), 26)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (108, N'Xám', CAST(0x0000AD350136D2AB AS DateTime), 5, N'Áo khoác nỉ Spider-man Xám 8', N'8', CAST(0x0000AD3900A08D8A AS DateTime), 26)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (109, N'Đỏ', CAST(0x0000AD350136D2AB AS DateTime), 1, N'Áo khoác nỉ Spider-man Đỏ 10', N'10', CAST(0x0000AD3900A08D8A AS DateTime), 26)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (110, N'Xanh', CAST(0x0000AD3501379D57 AS DateTime), 15, N'Quần short jean #1 Xanh 6', N'6', CAST(0x0000AD3501379D57 AS DateTime), 27)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (111, N'Đen', CAST(0x0000AD3501379D57 AS DateTime), 11, N'Quần short jean #1 Đen 6', N'6', CAST(0x0000AD3501379D57 AS DateTime), 27)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (112, N'Đen', CAST(0x0000AD3501379D57 AS DateTime), 3, N'Quần short jean #1 Đen 8', N'8', CAST(0x0000AD3501379D57 AS DateTime), 27)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (113, N'Trắng', CAST(0x0000AD3501393330 AS DateTime), 12, N'Áo sơ mi ngắn tay #1 Trắng 6', N'6', CAST(0x0000AD3900C8295F AS DateTime), 28)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (114, N'Trắng', CAST(0x0000AD3501393331 AS DateTime), 14, N'Áo sơ mi ngắn tay #1 Trắng 8', N'8', CAST(0x0000AD3900C8295F AS DateTime), 28)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (115, N'Trắng', CAST(0x0000AD3501393332 AS DateTime), 6, N'Áo sơ mi ngắn tay #1 Trắng 10', N'10', CAST(0x0000AD3900C8295F AS DateTime), 28)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (116, N'Đen', CAST(0x0000AD35013A2339 AS DateTime), 9, N'Quần sort bé trai #2 Đen 6', N'6', CAST(0x0000AD3700C6CBD9 AS DateTime), 29)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (117, N'Đen', CAST(0x0000AD35013A233A AS DateTime), 4, N'Quần sort bé trai #2 Đen 8', N'8', CAST(0x0000AD3700C381A3 AS DateTime), 29)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (118, N'Xanh', CAST(0x0000AD35013A233A AS DateTime), 13, N'Quần sort bé trai #2 Xanh 8', N'8', CAST(0x0000AD35013A233A AS DateTime), 29)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (119, N'Xanh', CAST(0x0000AD35013B10D0 AS DateTime), 10, N'Áo thun ngắn tay cá Sấu Xanh 6', N'6', CAST(0x0000AD3600AA998A AS DateTime), 30)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (120, N'Trắng', CAST(0x0000AD35013B10D1 AS DateTime), 5, N'Áo thun ngắn tay cá Sấu Trắng 6', N'6', CAST(0x0000AD3600AA998A AS DateTime), 30)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (121, N'Xanh', CAST(0x0000AD35013B10D1 AS DateTime), 14, N'Áo thun ngắn tay cá Sấu Xanh 8', N'8', CAST(0x0000AD3600AA998A AS DateTime), 30)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (122, N'Trắng', CAST(0x0000AD35013B10D1 AS DateTime), 3, N'Áo thun ngắn tay cá Sấu Trắng 8', N'8', CAST(0x0000AD3600AA998A AS DateTime), 30)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (123, N'Xanh', CAST(0x0000AD3501407F31 AS DateTime), 9, N'Áo khoác nỉ Captain America Xanh 6', N'6', CAST(0x0000AD3900C94A49 AS DateTime), 31)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (124, N'Xanh', CAST(0x0000AD3501407F32 AS DateTime), 15, N'Áo khoác nỉ Captain America Xanh 8', N'8', CAST(0x0000AD3900C94A49 AS DateTime), 31)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (125, N'Vàng', CAST(0x0000AD350141645C AS DateTime), 10, N'Áo thun ngắn tay T-Rex Vàng 6', N'6', CAST(0x0000AD350141645C AS DateTime), 32)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (126, N'Đỏ', CAST(0x0000AD350141645C AS DateTime), 15, N'Áo thun ngắn tay T-Rex Đỏ 6', N'6', CAST(0x0000AD350141645C AS DateTime), 32)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (127, N'Trắng', CAST(0x0000AD350141645C AS DateTime), 9, N'Áo thun ngắn tay T-Rex Trắng 6', N'6', CAST(0x0000AD350141645C AS DateTime), 32)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (128, N'Đỏ', CAST(0x0000AD350141645C AS DateTime), 5, N'Áo thun ngắn tay T-Rex Đỏ 8', N'8', CAST(0x0000AD350141645C AS DateTime), 32)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (129, N'Trắng', CAST(0x0000AD350141645C AS DateTime), 2, N'Áo thun ngắn tay T-Rex Trắng 8', N'8', CAST(0x0000AD3900CB5CB8 AS DateTime), 32)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (131, N'Xanh', CAST(0x0000AD350143315B AS DateTime), 24, N'Bộ thun Spider-man #1 Xanh 4', N'4', CAST(0x0000AD3700C63D0E AS DateTime), 33)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (132, N'Đỏ', CAST(0x0000AD350143315C AS DateTime), 30, N'Bộ thun Spider-man #1 Đỏ 4', N'4', CAST(0x0000AD350144AECC AS DateTime), 33)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (133, N'Xanh', CAST(0x0000AD350143315C AS DateTime), 13, N'Bộ thun Spider-man #1 Xanh 6', N'6', CAST(0x0000AD350144AECC AS DateTime), 33)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (134, N'Xanh', CAST(0x0000AD350143315C AS DateTime), 8, N'Bộ thun Spider-man #1 Xanh 8', N'8', CAST(0x0000AD350144AECC AS DateTime), 33)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (135, N'Đỏ', CAST(0x0000AD350143315C AS DateTime), 4, N'Bộ thun Spider-man #1 Đỏ 8', N'8', CAST(0x0000AD350144AECC AS DateTime), 33)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (136, N'Đen', CAST(0x0000AD350146F6D2 AS DateTime), 11, N'Bộ thun Spider-man #2 Đen 4', N'4', CAST(0x0000AD350146F6D2 AS DateTime), 34)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (137, N'Đen', CAST(0x0000AD350146F6D2 AS DateTime), 12, N'Bộ thun Spider-man #2 Đen 6', N'6', CAST(0x0000AD3900CBA878 AS DateTime), 34)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (138, N'Đen', CAST(0x0000AD350146F6D2 AS DateTime), 7, N'Bộ thun Spider-man #2 Đen 8', N'8', CAST(0x0000AD350146F6D2 AS DateTime), 34)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (139, N'Vàng', CAST(0x0000AD35014A1909 AS DateTime), 15, N'Áo thun Hoddie #1 Vàng 4', N'4', CAST(0x0000AD3600AD89D2 AS DateTime), 35)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (140, N'Trắng', CAST(0x0000AD35014A1909 AS DateTime), 11, N'Áo thun Hoddie #1 Trắng 4', N'4', CAST(0x0000AD3600AD89D2 AS DateTime), 35)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (141, N'Hồng', CAST(0x0000AD35014A1909 AS DateTime), 5, N'Áo thun Hoddie #1 Hồng 4', N'4', CAST(0x0000AD3600AD89D2 AS DateTime), 35)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (142, N'Vàng', CAST(0x0000AD35014A190A AS DateTime), 5, N'Áo thun Hoddie #1 Vàng 6', N'6', CAST(0x0000AD3600AD89D2 AS DateTime), 35)
GO
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (143, N'Trắng', CAST(0x0000AD35014A190A AS DateTime), 4, N'Áo thun Hoddie #1 Trắng 8', N'8', CAST(0x0000AD3600AD89D2 AS DateTime), 35)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (144, N'Hồng', CAST(0x0000AD35014A190A AS DateTime), 3, N'Áo thun Hoddie #1 Hồng 10', N'10', CAST(0x0000AD3600AD89D2 AS DateTime), 35)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (145, N'Vàng', CAST(0x0000AD35014A190A AS DateTime), 8, N'Áo thun Hoddie #1 Vàng 12', N'12', CAST(0x0000AD3600AD89D2 AS DateTime), 35)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (146, N'Hồng', CAST(0x0000AD35014C9649 AS DateTime), 49, N'Đầm bé gái #1 Hồng 5', N'5', CAST(0x0000AD3700C5F37B AS DateTime), 36)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (147, N'Hồng', CAST(0x0000AD35014C9649 AS DateTime), 22, N'Đầm bé gái #1 Hồng 7', N'7', CAST(0x0000AD3900B332DE AS DateTime), 36)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (148, N'Hồng', CAST(0x0000AD35014C9649 AS DateTime), 1, N'Đầm bé gái #1 Hồng 9', N'9', CAST(0x0000AD3700BFCF75 AS DateTime), 36)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (149, N'Đen', CAST(0x0000AD3700CAA763 AS DateTime), 15, N'Bộ Jeans Lửng Mickey Đen 2', N'2', CAST(0x0000AD3700CAA763 AS DateTime), 37)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (150, N'Đen', CAST(0x0000AD3700CAA763 AS DateTime), 13, N'Bộ Jeans Lửng Mickey Đen 4', N'4', CAST(0x0000AD3700CAA763 AS DateTime), 37)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (151, N'Trắng', CAST(0x0000AD3700CAA764 AS DateTime), 11, N'Bộ Jeans Lửng Mickey Trắng 4', N'4', CAST(0x0000AD3700CAA764 AS DateTime), 37)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (152, N'Đen', CAST(0x0000AD3700CAA764 AS DateTime), 5, N'Bộ Jeans Lửng Mickey Đen 6', N'6', CAST(0x0000AD3700CAA764 AS DateTime), 37)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (153, N'Trắng', CAST(0x0000AD3700CAA764 AS DateTime), 3, N'Bộ Jeans Lửng Mickey Trắng 9', N'9', CAST(0x0000AD3700CAA764 AS DateTime), 37)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (154, N'nMàu', CAST(0x0000AD3700CBEB35 AS DateTime), 3, N'Đầm sọc nhiều màu Đa Sắc 2', N'2', CAST(0x0000AD3700CBEB35 AS DateTime), 38)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (155, N'nMàu', CAST(0x0000AD3700CBEB35 AS DateTime), 12, N'Đầm sọc nhiều màu Đa Sắc 4', N'4', CAST(0x0000AD3900CBD896 AS DateTime), 38)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (156, N'nMàu', CAST(0x0000AD3700CBEB35 AS DateTime), 40, N'Đầm sọc nhiều màu Đa Sắc 7', N'7', CAST(0x0000AD3900CBD896 AS DateTime), 38)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (157, N'nMàu', CAST(0x0000AD3700CBEB35 AS DateTime), 12, N'Đầm sọc nhiều màu Đa Sắc 9', N'9', CAST(0x0000AD3800DBDBDD AS DateTime), 38)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (158, N'Cam', CAST(0x0000AD3700CECAD2 AS DateTime), 13, N'Set đồ khủng long #1 Cam 4', N'4', CAST(0x0000AD3700CECAD2 AS DateTime), 39)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (159, N'Đỏ', CAST(0x0000AD3700CECAD2 AS DateTime), 11, N'Set đồ khủng long #1 Đỏ 4', N'4', CAST(0x0000AD3700CECAD2 AS DateTime), 39)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (160, N'Cam', CAST(0x0000AD3700CECAD2 AS DateTime), 5, N'Set đồ khủng long #1 Cam 6', N'6', CAST(0x0000AD3700CECAD2 AS DateTime), 39)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (161, N'Đỏ', CAST(0x0000AD3700CECAD2 AS DateTime), 33, N'Set đồ khủng long #1 Đỏ 8', N'8', CAST(0x0000AD3700CECAD2 AS DateTime), 39)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (162, N'Cam', CAST(0x0000AD3700CECAD2 AS DateTime), 11, N'Set đồ khủng long #1 Cam 8', N'8', CAST(0x0000AD3700CECAD2 AS DateTime), 39)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (163, N'Xám', CAST(0x0000AD3700D15376 AS DateTime), 10, N'Áo thun Ant-man Xám 7', N'7', CAST(0x0000AD3900CB9286 AS DateTime), 40)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (164, N'Xám', CAST(0x0000AD3700D15376 AS DateTime), 19, N'Áo thun Ant-man Xám 11', N'11', CAST(0x0000AD3900C944F2 AS DateTime), 40)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (165, N'Xám', CAST(0x0000AD3700D15376 AS DateTime), 4, N'Áo thun Ant-man Xám 14', N'14', CAST(0x0000AD3900C944F2 AS DateTime), 40)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (166, N'Xanh', CAST(0x0000AD3700D2FA0C AS DateTime), 17, N'Áo thun in hình Cá Sấu Xanh 7', N'7', CAST(0x0000AD3700D2FA0C AS DateTime), 41)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (167, N'Xanh', CAST(0x0000AD3700D2FA0C AS DateTime), 9, N'Áo thun in hình Cá Sấu Xanh 9', N'9', CAST(0x0000AD3700D2FA0C AS DateTime), 41)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (168, N'Xanh', CAST(0x0000AD3700D2FA0C AS DateTime), 1, N'Áo thun in hình Cá Sấu Xanh 11', N'11', CAST(0x0000AD3700D2FA0C AS DateTime), 41)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (169, N'Xanh', CAST(0x0000AD3700D76E50 AS DateTime), 15, N'Áo thun in loang Xanh 5', N'5', CAST(0x0000AD3700D76E50 AS DateTime), 42)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (170, N'Xanh', CAST(0x0000AD3700D76E52 AS DateTime), 7, N'Áo thun in loang Xanh 7', N'7', CAST(0x0000AD3700D76E52 AS DateTime), 42)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (171, N'Xanh', CAST(0x0000AD3700D76E52 AS DateTime), 12, N'Áo thun in loang Xanh 9', N'9', CAST(0x0000AD3700D76E52 AS DateTime), 42)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (172, N'Đỏ', CAST(0x0000AD3700D8A914 AS DateTime), 15, N'Áo thun Mèo Sasy Đỏ 5', N'5', CAST(0x0000AD3700D8A914 AS DateTime), 43)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (173, N'Đỏ', CAST(0x0000AD3700D8A915 AS DateTime), 10, N'Áo thun Mèo Sasy Đỏ 7', N'7', CAST(0x0000AD3900CCBCD8 AS DateTime), 43)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (174, N'Đỏ', CAST(0x0000AD3700D8A915 AS DateTime), 7, N'Áo thun Mèo Sasy Đỏ 9', N'9', CAST(0x0000AD3700D8A915 AS DateTime), 43)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (175, N'Trắng', CAST(0x0000AD3700D968DD AS DateTime), 7, N'Đầm ren trắng Trắng 7', N'7', CAST(0x0000AD3700D968DD AS DateTime), 44)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (176, N'Trắng', CAST(0x0000AD3700D968DE AS DateTime), 9, N'Đầm ren trắng Trắng 9', N'9', CAST(0x0000AD3700D968DE AS DateTime), 44)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (177, N'Trắng', CAST(0x0000AD3700D968DE AS DateTime), 4, N'Đầm ren trắng Trắng 11', N'11', CAST(0x0000AD3700D968DE AS DateTime), 44)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (178, N'Trắng', CAST(0x0000AD3700DA2F41 AS DateTime), 17, N'Đầm ren trắng cao cấp Trắng 7', N'7', CAST(0x0000AD3700DA2F41 AS DateTime), 45)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (179, N'Trắng', CAST(0x0000AD3700DA2F41 AS DateTime), 10, N'Đầm ren trắng cao cấp Trắng 9', N'9', CAST(0x0000AD3800DED358 AS DateTime), 45)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (180, N'Trắng', CAST(0x0000AD3700DA2F41 AS DateTime), 7, N'Đầm ren trắng cao cấp Trắng 13', N'13', CAST(0x0000AD3700DA2F41 AS DateTime), 45)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (181, N'Xanh', CAST(0x0000AD3700DB22CC AS DateTime), 11, N'Áo thun GNARLY Xanh 6', N'6', CAST(0x0000AD3700DB22CC AS DateTime), 46)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (182, N'Xanh', CAST(0x0000AD3700DB22CD AS DateTime), 16, N'Áo thun GNARLY Xanh 8', N'8', CAST(0x0000AD3700DB22CD AS DateTime), 46)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (183, N'Xanh', CAST(0x0000AD3700DB22CD AS DateTime), 7, N'Áo thun GNARLY Xanh 14', N'14', CAST(0x0000AD3700DB22CD AS DateTime), 46)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (184, N'Đen', CAST(0x0000AD3700DBC3B0 AS DateTime), 12, N'Quần Jeans dài #1 Đen 12', N'12', CAST(0x0000AD3700DBC3B0 AS DateTime), 47)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (185, N'Xanh', CAST(0x0000AD3700DBC3B0 AS DateTime), 11, N'Quần Jeans dài #1 Xanh 12', N'12', CAST(0x0000AD3700DBC3B0 AS DateTime), 47)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (186, N'Đen', CAST(0x0000AD3700DBC3B1 AS DateTime), 14, N'Quần Jeans dài #1 Đen 14', N'14', CAST(0x0000AD3700DBC3B1 AS DateTime), 47)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (187, N'Đen', CAST(0x0000AD3700DBC3B1 AS DateTime), 5, N'Quần Jeans dài #1 Đen 16', N'16', CAST(0x0000AD3700DBC3B1 AS DateTime), 47)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (188, N'Đen', CAST(0x0000AD3700DCB684 AS DateTime), 15, N'Bộ thun Mickey #1 Đen 5', N'5', CAST(0x0000AD3700DCB684 AS DateTime), 48)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (189, N'Trăng', CAST(0x0000AD3700DCB685 AS DateTime), 5, N'Bộ thun Mickey #1 Trăng 5', N'5', CAST(0x0000AD3700DCB685 AS DateTime), 48)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (190, N'Đen', CAST(0x0000AD3700DCB685 AS DateTime), 12, N'Bộ thun Mickey #1 Đen 7', N'7', CAST(0x0000AD3700DCB685 AS DateTime), 48)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (191, N'Đen', CAST(0x0000AD3700DCB685 AS DateTime), 8, N'Bộ thun Mickey #1 Đen 9', N'9', CAST(0x0000AD3700DCB685 AS DateTime), 48)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (192, N'Trắng', CAST(0x0000AD3700DD7EAC AS DateTime), 3, N'Bộ thun mùa hè Trắng 6', N'6', CAST(0x0000AD3700DD7EAC AS DateTime), 49)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (193, N'Trắng', CAST(0x0000AD3700DD7EAC AS DateTime), 7, N'Bộ thun mùa hè Trắng 8', N'8', CAST(0x0000AD3900CCBCD8 AS DateTime), 49)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (194, N'Trắng', CAST(0x0000AD3700DD7EAC AS DateTime), 14, N'Bộ thun mùa hè Trắng 10', N'10', CAST(0x0000AD3700DD7EAC AS DateTime), 49)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (195, N'Trắng', CAST(0x0000AD3700DE5EEC AS DateTime), 10, N'Set đồ cho bé gái Trắng 10', N'10', CAST(0x0000AD3700DE5EEC AS DateTime), 50)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (196, N'Trắng', CAST(0x0000AD3700DE5EEC AS DateTime), 14, N'Set đồ cho bé gái Trắng 11', N'11', CAST(0x0000AD3900B9A3F2 AS DateTime), 50)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (197, N'Trắng', CAST(0x0000AD3700DE5EEC AS DateTime), 11, N'Set đồ cho bé gái Trắng 13', N'13', CAST(0x0000AD3700DE5EEC AS DateTime), 50)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (198, N'Trắng', CAST(0x0000AD3700DE5EEC AS DateTime), 6, N'Set đồ cho bé gái Trắng 16', N'16', CAST(0x0000AD3700DE5EEC AS DateTime), 50)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (199, N'Cam', CAST(0x0000AD37013D31C8 AS DateTime), 11, N'Bộ đồ trẻ em Zebra Cam 1', N'1', CAST(0x0000AD37015A835F AS DateTime), 51)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (200, N'Cam', CAST(0x0000AD37013D31C9 AS DateTime), 13, N'Bộ đồ trẻ em Zebra Cam 3', N'3', CAST(0x0000AD37015A835F AS DateTime), 51)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (201, N'Cam', CAST(0x0000AD37013D31C9 AS DateTime), 25, N'Bộ đồ trẻ em Zebra Cam 5', N'5', CAST(0x0000AD37015A835F AS DateTime), 51)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (202, N'Đen', CAST(0x0000AD37015B3EB8 AS DateTime), 16, N'Áo thun Batman Đen 8', N'8', CAST(0x0000AD3900CD06CA AS DateTime), 52)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (203, N'Trắng', CAST(0x0000AD37015B3EB9 AS DateTime), 11, N'Áo thun Batman Trắng 8', N'8', CAST(0x0000AD3900CD06CA AS DateTime), 52)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (204, N'Đen', CAST(0x0000AD37015B3EB9 AS DateTime), 1, N'Áo thun Batman Đen 12', N'12', CAST(0x0000AD3900CD06CA AS DateTime), 52)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (205, N'Đen', CAST(0x0000AD37015C6147 AS DateTime), 12, N'Áo thun Super-Man Đen 8', N'8', CAST(0x0000AD37015D3B0B AS DateTime), 53)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (206, N'Trắng', CAST(0x0000AD37015C6147 AS DateTime), 11, N'Áo thun Super-Man Trắng 8', N'8', CAST(0x0000AD37015D3B0B AS DateTime), 53)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (207, N'Đen', CAST(0x0000AD37015C6147 AS DateTime), 3, N'Áo thun Super-Man Đen 12', N'12', CAST(0x0000AD37015D3B0B AS DateTime), 53)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (208, N'Trắng', CAST(0x0000AD37015C6147 AS DateTime), 17, N'Áo thun Super-Man Trắng 14', N'14', CAST(0x0000AD37015D3B0B AS DateTime), 53)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (282, N'Xám', CAST(0x0000AD37017A09E8 AS DateTime), 11, N'Áo thun Khủng long #2 Xám 8', N'8', CAST(0x0000AD3900CCD1A9 AS DateTime), 69)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (283, N'Xám', CAST(0x0000AD37017A09E9 AS DateTime), 9, N'Áo thun Khủng long #2 Xám 10', N'10', CAST(0x0000AD3900CCD1A9 AS DateTime), 69)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (284, N'Xám', CAST(0x0000AD37017A09E9 AS DateTime), 8, N'Áo thun Khủng long #2 Xám 12', N'12', CAST(0x0000AD3900CCD1A9 AS DateTime), 69)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (285, N'Đỏ', CAST(0x0000AD3800C64B8A AS DateTime), 17, N'Bộ thể thao #1 Đỏ 7', N'7', CAST(0x0000AD3800C64B8A AS DateTime), 70)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (286, N'Vàng', CAST(0x0000AD3800C64B8A AS DateTime), 11, N'Bộ thể thao #1 Vàng 7', N'7', CAST(0x0000AD3800C64B8A AS DateTime), 70)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (287, N'Xanh', CAST(0x0000AD3800C64B8B AS DateTime), 9, N'Bộ thể thao #1 Xanh 9', N'9', CAST(0x0000AD3800C64B8B AS DateTime), 70)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (288, N'Đỏ', CAST(0x0000AD3800C64B8B AS DateTime), 11, N'Bộ thể thao #1 Đỏ 11', N'11', CAST(0x0000AD3800C64B8B AS DateTime), 70)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (289, N'Đỏ', CAST(0x0000AD3800C64B8B AS DateTime), 11, N'Bộ thể thao #1 Đỏ 15', N'15', CAST(0x0000AD3800C64B8B AS DateTime), 70)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (290, N'Xanh', CAST(0x0000AD3800C64B8B AS DateTime), 13, N'Bộ thể thao #1 Xanh 15', N'15', CAST(0x0000AD3800C64B8B AS DateTime), 70)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (291, N'Vàng', CAST(0x0000AD3800C64B8B AS DateTime), 16, N'Bộ thể thao #1 Vàng 13', N'13', CAST(0x0000AD3900CC6492 AS DateTime), 70)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (292, N'Xám', CAST(0x0000AD3900C510F1 AS DateTime), 11, N'Quần ngắn trẻ em #1 Xám 1', N'1', CAST(0x0000AD3900C510F1 AS DateTime), 71)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (293, N'Xám', CAST(0x0000AD3900C510F1 AS DateTime), 7, N'Quần ngắn trẻ em #1 Xám 2', N'2', CAST(0x0000AD3900C510F1 AS DateTime), 71)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (294, N'Xám', CAST(0x0000AD3900C510F1 AS DateTime), 9, N'Quần ngắn trẻ em #1 Xám 3', N'3', CAST(0x0000AD3900C510F1 AS DateTime), 71)
SET IDENTITY_INSERT [dbo].[SubProduct] OFF
SET IDENTITY_INSERT [dbo].[Supplier] ON 

INSERT [dbo].[Supplier] ([id], [address], [email], [name], [phone]) VALUES (2, N'Số 129+131 Nguyễn Văn Bảo Quận Gò Vấp Thành phố Hồ Chí Minh', N'minhanhnguyen172@gmail.com', N'Công ty Cổ Phần May mặc Gia Định', N'0909273403')
INSERT [dbo].[Supplier] ([id], [address], [email], [name], [phone]) VALUES (3, N'21 Đường Lê Thúc Hoạch, Phú Thọ Hoà, Tân Phú, Thành phố Hồ Chí Minh', N'Kidstyle@gmail.com', N'Kidstyle', N'0909145138')
INSERT [dbo].[Supplier] ([id], [address], [email], [name], [phone]) VALUES (4, N'71/24 Nguyễn Bặc, Phường 3, Quận Tân bình, TP.Hồ Chí Minh', N'CongTyBicokids@gmail.com', N'Bicokids', N'0938667909')
INSERT [dbo].[Supplier] ([id], [address], [email], [name], [phone]) VALUES (5, N'	146 Tân Kỳ Tân Quý, P. Sơn Kỳ, Q. Tân Phú. TP.HCM.', N'info@veco.com.vn', N'VECO1', N'0901455079')
SET IDENTITY_INSERT [dbo].[Supplier] OFF
SET ANSI_PADDING ON

GO
/****** Object:  Index [UK_de34gsw4qt8auhffbna969ahp]    Script Date: 06/02/2021 12:47:00 ******/
ALTER TABLE [dbo].[Account] ADD  CONSTRAINT [UK_de34gsw4qt8auhffbna969ahp] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UK_3qgg01qojcmbdp47dkaom9x45]    Script Date: 06/02/2021 12:47:00 ******/
ALTER TABLE [dbo].[Customer] ADD  CONSTRAINT [UK_3qgg01qojcmbdp47dkaom9x45] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Account_Role]  WITH CHECK ADD  CONSTRAINT [FK2tt2mvtbw3g5qiq0ns3p5ajxt] FOREIGN KEY([role_id])
REFERENCES [dbo].[Roles] ([id])
GO
ALTER TABLE [dbo].[Account_Role] CHECK CONSTRAINT [FK2tt2mvtbw3g5qiq0ns3p5ajxt]
GO
ALTER TABLE [dbo].[Account_Role]  WITH CHECK ADD  CONSTRAINT [FKpwmk9tjqagepe78s1iod2kqu0] FOREIGN KEY([account_id])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Account_Role] CHECK CONSTRAINT [FKpwmk9tjqagepe78s1iod2kqu0]
GO
ALTER TABLE [dbo].[Customer]  WITH CHECK ADD  CONSTRAINT [FKeqp4idmxgvl1jq3bm0rkfiqy8] FOREIGN KEY([account_id])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Customer] CHECK CONSTRAINT [FKeqp4idmxgvl1jq3bm0rkfiqy8]
GO
ALTER TABLE [dbo].[ImagesUrl]  WITH CHECK ADD  CONSTRAINT [FKqjwq60r61v0wnt8ges4bm0eiv] FOREIGN KEY([product_id])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[ImagesUrl] CHECK CONSTRAINT [FKqjwq60r61v0wnt8ges4bm0eiv]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK23j7m7olnvgnrii52hp24hc2l] FOREIGN KEY([subProduct_id])
REFERENCES [dbo].[SubProduct] ([id])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK23j7m7olnvgnrii52hp24hc2l]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK7kn2p8fks7ft0mnb10i3cklej] FOREIGN KEY([order_id])
REFERENCES [dbo].[Orders] ([id])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK7kn2p8fks7ft0mnb10i3cklej]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FKhwn935tudm12n4ihi91mnm0w5] FOREIGN KEY([customer_id])
REFERENCES [dbo].[Customer] ([id])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FKhwn935tudm12n4ihi91mnm0w5]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK11uleikow9eaenolp88xnaudd] FOREIGN KEY([supplier_id])
REFERENCES [dbo].[Supplier] ([id])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK11uleikow9eaenolp88xnaudd]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FKexqqeaksnmmku5py194ywp140] FOREIGN KEY([category_id])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FKexqqeaksnmmku5py194ywp140]
GO
ALTER TABLE [dbo].[SubProduct]  WITH CHECK ADD  CONSTRAINT [FK2889lqosdcs0q2nmoqie3upqg] FOREIGN KEY([product_id])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[SubProduct] CHECK CONSTRAINT [FK2889lqosdcs0q2nmoqie3upqg]
GO
