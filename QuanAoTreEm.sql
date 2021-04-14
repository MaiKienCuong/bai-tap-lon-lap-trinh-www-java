USE [QuanAoTreEm]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 04/15/2021 05:28:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Account](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[email] [varchar](50) NULL,
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
/****** Object:  Table [dbo].[Account_Role]    Script Date: 04/15/2021 05:28:13 ******/
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
/****** Object:  Table [dbo].[Category]    Script Date: 04/15/2021 05:28:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Customer]    Script Date: 04/15/2021 05:28:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Customer](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[phone] [varchar](50) NULL,
	[account_id] [bigint] NULL,
	[typeCustomer_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 04/15/2021 05:28:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[price] [numeric](19, 2) NOT NULL,
	[quantity] [int] NOT NULL,
	[order_id] [bigint] NULL,
	[subProduct_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Orders]    Script Date: 04/15/2021 05:28:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[order_date] [date] NULL,
	[payment_method] [nvarchar](255) NULL,
	[ship_address] [nvarchar](255) NULL,
	[status] [nvarchar](255) NULL,
	[total] [numeric](19, 2) NULL,
	[customer_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Product]    Script Date: 04/15/2021 05:28:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Product](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[active] [bit] NOT NULL,
	[created_at] [date] NULL,
	[discount] [float] NULL,
	[images_folder] [varchar](255) NULL,
	[long_description] [ntext] NULL,
	[marker] [nvarchar](50) NULL,
	[material] [nvarchar](255) NULL,
	[name] [nvarchar](255) NULL,
	[origin] [nvarchar](255) NULL,
	[price] [numeric](19, 2) NULL,
	[short_description] [ntext] NULL,
	[tax] [float] NULL,
	[updated_at] [date] NULL,
	[url] [varchar](255) NULL,
	[views] [int] NULL,
	[category_id] [bigint] NULL,
	[supplier_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Roles]    Script Date: 04/15/2021 05:28:13 ******/
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
/****** Object:  Table [dbo].[SubProduct]    Script Date: 04/15/2021 05:28:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[SubProduct](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [date] NULL,
	[color] [nvarchar](50) NULL,
	[inventory] [int] NULL,
	[size] [nvarchar](50) NULL,
	[name] [nvarchar](255) NULL,
	[sku] [varchar](50) NULL,
	[updated_at] [date] NULL,
	[product_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Supplier]    Script Date: 04/15/2021 05:28:13 ******/
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
/****** Object:  Table [dbo].[TypeCustomer]    Script Date: 04/15/2021 05:28:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TypeCustomer](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[preferential] [float] NULL,
	[type] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (22, N'cuongmaikien@gmail.com', 1, N'$2a$10$UuLTn7/il0wUc0i1N7qLpeukqY0h30HCB7Fd9BkClxuoE7QUei5V6', N'cuongmaikien')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (23, N'maikiencuong@gmail.com', 1, N'$2a$10$xxA8LBfuXPlDMGxWsKYRDe6AKqm0qa2UiftcYccPRF6xS7LaHbnG6', N'maikiencuong')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (24, N'nguyenvananh@gmail.com', 1, N'$2a$10$ySxRG42haseyZSVawNvz5u7ffJFSHUd6YuXjCOfkFgYMVHjv8E5Iy', N'nguyenvananh')
SET IDENTITY_INSERT [dbo].[Account] OFF
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (22, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (23, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (24, 1)
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([id], [name]) VALUES (1, N'Áo Thun Nam Trẻ Em')
INSERT [dbo].[Category] ([id], [name]) VALUES (2, N'Váy Nữ')
INSERT [dbo].[Category] ([id], [name]) VALUES (3, N'Quần Dài Nam Trẻ Em')
INSERT [dbo].[Category] ([id], [name]) VALUES (4, N'Quần Dài Nữ Trẻ Em')
INSERT [dbo].[Category] ([id], [name]) VALUES (5, N'Áo Thun Nữ Trẻ Em')
SET IDENTITY_INSERT [dbo].[Category] OFF
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (21, N'Cường Mai Kiên', N'0961516942', 22, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (22, N'Mai Kiên Cường', N'0961516941', 23, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (23, N'Nguyễn Văn Anh', N'123456789', 24, 1)
SET IDENTITY_INSERT [dbo].[Customer] OFF
SET IDENTITY_INSERT [dbo].[OrderDetail] ON 

INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (1, CAST(250000.00 AS Numeric(19, 2)), 1, 1, 1)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (2, CAST(250000.00 AS Numeric(19, 2)), 1, 1, 2)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (3, CAST(250000.00 AS Numeric(19, 2)), 1, 1, 3)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (4, CAST(250000.00 AS Numeric(19, 2)), 1, 1, 4)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (5, CAST(300000.00 AS Numeric(19, 2)), 1, 1, 5)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (6, CAST(300000.00 AS Numeric(19, 2)), 1, 1, 6)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (7, CAST(300000.00 AS Numeric(19, 2)), 1, 2, 7)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (8, CAST(300000.00 AS Numeric(19, 2)), 1, 2, 8)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (9, CAST(300000.00 AS Numeric(19, 2)), 1, 2, 9)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (10, CAST(300000.00 AS Numeric(19, 2)), 1, 2, 10)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (11, CAST(29000.00 AS Numeric(19, 2)), 1, 2, 11)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (12, CAST(29000.00 AS Numeric(19, 2)), 1, 2, 12)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (13, CAST(29000.00 AS Numeric(19, 2)), 1, 3, 13)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (14, CAST(29000.00 AS Numeric(19, 2)), 1, 3, 14)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (15, CAST(29000.00 AS Numeric(19, 2)), 1, 3, 15)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (16, CAST(29000.00 AS Numeric(19, 2)), 1, 3, 16)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (17, CAST(250000.00 AS Numeric(19, 2)), 1, 3, 17)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (18, CAST(250000.00 AS Numeric(19, 2)), 1, 3, 18)
SET IDENTITY_INSERT [dbo].[OrderDetail] OFF
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (1, CAST(0x66420B00 AS Date), N'ship cod', N'trường đại học công nghiệp thành phố hồ chí minh', N'đã xác nhận', CAST(1600000.00 AS Numeric(19, 2)), 21)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (2, CAST(0x66420B00 AS Date), N'ship cod', N'hà trung thanh hóa', N'đang chờ', CAST(1258000.00 AS Numeric(19, 2)), 22)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (3, CAST(0x66420B00 AS Date), N'ship cod', N'vinh nghệ an', N'đang chờ', CAST(616000.00 AS Numeric(19, 2)), 23)
SET IDENTITY_INSERT [dbo].[Orders] OFF
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [images_folder], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [url], [views], [category_id], [supplier_id]) VALUES (1, 1, CAST(0x61420B00 AS Date), 0, N'folder1', N'áo thun nam khủng long dành cho trẻ từ 5-6 tuổi, hợp thời trang, chất liệu cotton thoáng mát, thấm hút mồ hôi, mềm mại với da', N'HOT', N'100% cotton', N'ÁO THUN NAM KHỦNG LONG', N'Hồ Chí Minh Việt Nam', CAST(250000.00 AS Numeric(19, 2)), N'áo thun nam khủng long dành cho trẻ từ 5-6 tuổi, hợp thời trang, chất liệu cotton thoáng mát, thấm hút mồ hôi, mềm mại với da', 0, CAST(0x61420B00 AS Date), N'img1.jpg', 123, 1, 1)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [images_folder], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [url], [views], [category_id], [supplier_id]) VALUES (2, 1, CAST(0x60420B00 AS Date), 0.05, N'folder2', N'váy nữ dành cho trẻ từ 5-6 tuổi, chất liệu thoáng mát, mềm mại với da', N'DIS', N'100% cotton', N'VÁY NỮ', N'Hồ Chí Minh Việt Nam', CAST(300000.00 AS Numeric(19, 2)), N'váy nữ dành cho trẻ từ 5-6 tuổi, chất liệu thoáng mát, mềm mại với da', 0, CAST(0x60420B00 AS Date), N'img2.jpg', 100, 2, 1)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [images_folder], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [url], [views], [category_id], [supplier_id]) VALUES (3, 1, CAST(0x5F420B00 AS Date), 0, N'folder3', N'quần dài dành cho trẻ từ 5-6 tuổi, chất liệu thoáng mát, mềm mại với da', N'DEF', N'100% cotton', N'QUẦN DÀI TRẺ NAM', N'Hồ Chí Minh Việt Nam', CAST(29000.00 AS Numeric(19, 2)), N'quần dài dành cho trẻ từ 5-6 tuổi, chất liệu thoáng mát, mềm mại với da', 0, CAST(0x5F420B00 AS Date), N'img3.jpg', 200, 3, 1)
SET IDENTITY_INSERT [dbo].[Product] OFF
SET IDENTITY_INSERT [dbo].[Roles] ON 

INSERT [dbo].[Roles] ([id], [role]) VALUES (1, N'ROLE_CUSTOMER')
INSERT [dbo].[Roles] ([id], [role]) VALUES (2, N'ROLE_ADMIN')
SET IDENTITY_INSERT [dbo].[Roles] OFF
SET IDENTITY_INSERT [dbo].[SubProduct] ON 

INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (1, CAST(0x57420B00 AS Date), N'Đỏ', 7, N'S', N'ÁO THUN NAM ĐỎ SIZE S', N'1-THUNNAM-DO-S', CAST(0x57420B00 AS Date), 1)
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (2, CAST(0x57420B00 AS Date), N'Đỏ', 8, N'M', N'ÁO THUN NAM ĐỎ SIZE M', N'1-THUNNAM-DO-M', CAST(0x57420B00 AS Date), 1)
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (3, CAST(0x57420B00 AS Date), N'Xanh', 9, N'M', N'ÁO THUN NAM XANH SIZE M', N'1-THUNNAM-XA-S', CAST(0x57420B00 AS Date), 1)
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (4, CAST(0x57420B00 AS Date), N'Xanh', 10, N'L', N'ÁO THUN NAM XANH SIZE L', N'1-THUNNAM-XA-M', CAST(0x57420B00 AS Date), 1)
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (5, CAST(0x57420B00 AS Date), N'Đỏ', 5, N'S', N'VÁY NỮ ĐỎ SIZE S', N'2-VAYNU-DO-S', CAST(0x57420B00 AS Date), 2)
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (6, CAST(0x57420B00 AS Date), N'Đỏ', 6, N'M', N'VÁY NỮ ĐỎ SIZE M', N'2-VAYNU-DO-M', CAST(0x57420B00 AS Date), 2)
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (7, CAST(0x57420B00 AS Date), N'Đỏ', 6, N'L', N'VÁY NỮ ĐỎ SIZE L', N'2-VAYNU-DO-L', CAST(0x57420B00 AS Date), 2)
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (8, CAST(0x57420B00 AS Date), N'Hồng', 7, N'S', N'VÁY NỮ HỒNG SIZE S', N'2-VAYNU-HO-S', CAST(0x57420B00 AS Date), 2)
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (9, CAST(0x57420B00 AS Date), N'Hồng', 7, N'M', N'VÁY NỮ HỒNG SIZE M', N'2-VAYNU-HO-M', CAST(0x57420B00 AS Date), 2)
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (10, CAST(0x57420B00 AS Date), N'Hồng', 8, N'L', N'VÁY NỮ HỒNG SIZE L', N'2-VAYNU-HO-L', CAST(0x57420B00 AS Date), 2)
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (11, CAST(0x57420B00 AS Date), N'ĐEN', 5, N'S', N'QUẦN DÀI NAM ĐEN SIZE S', N'3-QUANNAM-DEN-S', CAST(0x57420B00 AS Date), 3)
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (12, CAST(0x57420B00 AS Date), N'ĐEN', 6, N'M', N'QUẦN DÀI NAM ĐEN SIZE M', N'3-QUANNAM-DEN-M', CAST(0x57420B00 AS Date), 3)
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (13, CAST(0x57420B00 AS Date), N'ĐEN', 7, N'L', N'QUẦN DÀI NAM ĐEN SIZE L', N'3-QUANNAM-DEN-L', CAST(0x57420B00 AS Date), 3)
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (14, CAST(0x57420B00 AS Date), N'NÂU', 8, N'S', N'QUẦN DÀI NAM NÂU SIZE S', N'3-QUANNAM-NAU-S', CAST(0x57420B00 AS Date), 3)
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (15, CAST(0x57420B00 AS Date), N'NÂU', 9, N'M', N'QUẦN DÀI NAM NÂU SIZE M', N'3-QUANNAM-NAU-M', CAST(0x57420B00 AS Date), 3)
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (16, CAST(0x57420B00 AS Date), N'NÂU', 4, N'L', N'QUẦN DÀI NAM NÂU SIZE L', N'3-QUANNAM-NAU-L', CAST(0x57420B00 AS Date), 3)
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (17, CAST(0x57420B00 AS Date), N'Đỏ', 0, N'L', N'ÁO THUN NAM ĐỎ SIZE L', N'1-THUNNAM-DO-L', CAST(0x57420B00 AS Date), 1)
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (18, CAST(0x57420B00 AS Date), N'Xanh', 0, N'S', N'ÁO THUN NAM XANH SIZE S', N'1-THUNNAM-XA-S', CAST(0x57420B00 AS Date), 1)
SET IDENTITY_INSERT [dbo].[SubProduct] OFF
SET IDENTITY_INSERT [dbo].[Supplier] ON 

INSERT [dbo].[Supplier] ([id], [address], [email], [name], [phone]) VALUES (1, N'Số 129+131 Lê Thanh Nghị, Hai Bà Trưng, Hà Nội', N'tuyetanh173@gmail.com', N'Công ty Cổ Phần May mặc Việt Tiến', N'0909273402')
SET IDENTITY_INSERT [dbo].[Supplier] OFF
SET IDENTITY_INSERT [dbo].[TypeCustomer] ON 

INSERT [dbo].[TypeCustomer] ([id], [preferential], [type]) VALUES (1, 0, N'NONE')
INSERT [dbo].[TypeCustomer] ([id], [preferential], [type]) VALUES (2, 100000, N'SILVER')
INSERT [dbo].[TypeCustomer] ([id], [preferential], [type]) VALUES (3, 2000000, N'GOLD')
INSERT [dbo].[TypeCustomer] ([id], [preferential], [type]) VALUES (4, 500000, N'DIAMOND')
SET IDENTITY_INSERT [dbo].[TypeCustomer] OFF
ALTER TABLE [dbo].[Account_Role]  WITH CHECK ADD  CONSTRAINT [FK2tt2mvtbw3g5qiq0ns3p5ajxt] FOREIGN KEY([role_id])
REFERENCES [dbo].[Roles] ([id])
GO
ALTER TABLE [dbo].[Account_Role] CHECK CONSTRAINT [FK2tt2mvtbw3g5qiq0ns3p5ajxt]
GO
ALTER TABLE [dbo].[Account_Role]  WITH CHECK ADD  CONSTRAINT [FKd71suikq4c5irpg6to4825elp] FOREIGN KEY([role_id])
REFERENCES [dbo].[Roles] ([id])
GO
ALTER TABLE [dbo].[Account_Role] CHECK CONSTRAINT [FKd71suikq4c5irpg6to4825elp]
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
ALTER TABLE [dbo].[Customer]  WITH CHECK ADD  CONSTRAINT [FKp2na0otfuhbr9se5jui1n5yb4] FOREIGN KEY([typeCustomer_id])
REFERENCES [dbo].[TypeCustomer] ([id])
GO
ALTER TABLE [dbo].[Customer] CHECK CONSTRAINT [FKp2na0otfuhbr9se5jui1n5yb4]
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
