USE [QuanAoTreEm]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 04/19/2021 07:48:55 ******/
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
/****** Object:  Table [dbo].[Account_Role]    Script Date: 04/19/2021 07:48:55 ******/
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
/****** Object:  Table [dbo].[Category]    Script Date: 04/19/2021 07:48:55 ******/
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
/****** Object:  Table [dbo].[Customer]    Script Date: 04/19/2021 07:48:55 ******/
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
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 04/19/2021 07:48:55 ******/
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
/****** Object:  Table [dbo].[Orders]    Script Date: 04/19/2021 07:48:55 ******/
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
/****** Object:  Table [dbo].[Product]    Script Date: 04/19/2021 07:48:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Product](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[active] [bit] NOT NULL,
	[created_at] [datetime] NULL,
	[discount] [float] NULL,
	[images_folder] [varchar](255) NULL,
	[long_description] [ntext] NULL,
	[marker] [nvarchar](50) NULL,
	[material] [nvarchar](255) NULL,
	[name] [nvarchar](255) NULL,
	[origin] [nvarchar](255) NULL,
	[price] [float] NULL,
	[short_description] [ntext] NULL,
	[tax] [float] NULL,
	[updated_at] [datetime] NULL,
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
/****** Object:  Table [dbo].[Roles]    Script Date: 04/19/2021 07:48:55 ******/
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
/****** Object:  Table [dbo].[SubProduct]    Script Date: 04/19/2021 07:48:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[SubProduct](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[color] [nvarchar](50) NULL,
	[created_at] [datetime] NULL,
	[inventory] [int] NULL,
	[name] [nvarchar](255) NULL,
	[size] [nvarchar](50) NULL,
	[sku] [varchar](50) NULL,
	[updated_at] [datetime] NULL,
	[product_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Supplier]    Script Date: 04/19/2021 07:48:55 ******/
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

INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (63, 1, N'$2a$10$7hStW/OPrqhupH.pLHOxf.oFjk4.zX.Okhz.UdqHObyfo36eDtJaO', N'emem')
INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (66, 1, N'$2a$10$g0OsqsvUKB8xNe09Mw8A3uNtbg55NeGPJEgGaTstUHQDOVLW8tJxa', N'anhem')
INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (67, 1, N'$2y$12$szYCxUTZoPXv4Bezfp5jjOHmOIYdCPugqmK6Ww4dD5l/ftoPbSgDG', N'admin')
SET IDENTITY_INSERT [dbo].[Account] OFF
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (63, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (66, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (67, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (67, 2)
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([id], [name]) VALUES (1, N'Áo Thun Nam Trẻ Em')
INSERT [dbo].[Category] ([id], [name]) VALUES (2, N'Váy Nữ')
INSERT [dbo].[Category] ([id], [name]) VALUES (3, N'Quần Dài Nam Trẻ Em')
INSERT [dbo].[Category] ([id], [name]) VALUES (4, N'Quần Dài Nữ Trẻ Em')
INSERT [dbo].[Category] ([id], [name]) VALUES (5, N'Áo Thun Nữ Trẻ Em')
SET IDENTITY_INSERT [dbo].[Category] OFF
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([id], [address], [email], [name], [phone], [account_id]) VALUES (60, N'Dương Quảng Hàm Gò Vấp TP Hồ Chí Minh', N'maikiencuongiuh@gmail.com', N'Cường Mai Kiên', N'0961516942', 63)
INSERT [dbo].[Customer] ([id], [address], [email], [name], [phone], [account_id]) VALUES (63, N'Dương Quảng Hàm Gò Vấp TP Hồ Chí Minh', N'kiencuongsasuke@gmail.com', N'Cường Mai Kiên', N'0961516942', 66)
SET IDENTITY_INSERT [dbo].[Customer] OFF
SET IDENTITY_INSERT [dbo].[OrderDetail] ON 

INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (7, 300000, 1, 2, 7)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (8, 300000, 1, 2, 8)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (9, 300000, 1, 2, 9)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (10, 300000, 1, 2, 10)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (11, 29000, 1, 2, 11)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (12, 29000, 1, 2, 12)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (13, 29000, 1, 3, 13)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (14, 29000, 1, 3, 14)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (15, 29000, 1, 3, 15)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (16, 29000, 1, 3, 16)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (17, 250000, 1, 3, 17)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (18, 250000, 1, 3, 18)
SET IDENTITY_INSERT [dbo].[OrderDetail] OFF
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (2, CAST(0x0000AD0B00000000 AS DateTime), N'COD', N'hà trung thanh hóa', N'PENDING', 1258000, 60)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (3, CAST(0x0000AD0B00000000 AS DateTime), N'COD', N'vinh nghệ an', N'PENDING', 616000, 63)
SET IDENTITY_INSERT [dbo].[Orders] OFF
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [images_folder], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [url], [views], [category_id], [supplier_id]) VALUES (1, 1, CAST(0x0000AD0600000000 AS DateTime), 0, N'folder1', N'áo thun nam khủng long dành cho trẻ từ 5-6 tuổi, hợp thời trang, chất liệu cotton thoáng mát, thấm hút mồ hôi, mềm mại với da', N'HOT', N'100% cotton', N'ÁO THUN NAM KHỦNG LONG', N'Hồ Chí Minh Việt Nam', 250000, N'áo thun nam khủng long dành cho trẻ từ 5-6 tuổi, hợp thời trang, chất liệu cotton thoáng mát, thấm hút mồ hôi, mềm mại với da', 0, CAST(0x0000AD0600000000 AS DateTime), N'img1.jpg', 123, 1, 1)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [images_folder], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [url], [views], [category_id], [supplier_id]) VALUES (2, 1, CAST(0x0000AD0500000000 AS DateTime), 0.05, N'folder2', N'váy nữ dành cho trẻ từ 5-6 tuổi, chất liệu thoáng mát, mềm mại với da', N'DIS', N'100% cotton', N'VÁY NỮ', N'Hồ Chí Minh Việt Nam', 300000, N'váy nữ dành cho trẻ từ 5-6 tuổi, chất liệu thoáng mát, mềm mại với da', 0, CAST(0x0000AD0500000000 AS DateTime), N'img2.jpg', 100, 2, 1)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [images_folder], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [url], [views], [category_id], [supplier_id]) VALUES (3, 1, CAST(0x0000AD0400000000 AS DateTime), 0, N'folder3', N'quần dài dành cho trẻ từ 5-6 tuổi, chất liệu thoáng mát, mềm mại với da', N'DEF', N'100% cotton', N'QUẦN DÀI TRẺ NAM', N'Hồ Chí Minh Việt Nam', 29000, N'quần dài dành cho trẻ từ 5-6 tuổi, chất liệu thoáng mát, mềm mại với da', 0, CAST(0x0000AD0400000000 AS DateTime), N'img3.jpg', 200, 3, 1)
SET IDENTITY_INSERT [dbo].[Product] OFF
SET IDENTITY_INSERT [dbo].[Roles] ON 

INSERT [dbo].[Roles] ([id], [role]) VALUES (1, N'ROLE_CUSTOMER')
INSERT [dbo].[Roles] ([id], [role]) VALUES (2, N'ROLE_ADMIN')
SET IDENTITY_INSERT [dbo].[Roles] OFF
SET IDENTITY_INSERT [dbo].[SubProduct] ON 

INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (1, N'Đỏ', CAST(0x0000ACFC00000000 AS DateTime), 7, N'ÁO THUN NAM ĐỎ SIZE S', N'S', N'1-THUNNAM-DO-S', CAST(0x0000ACFC00000000 AS DateTime), 1)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (2, N'Đỏ', CAST(0x0000ACFC00000000 AS DateTime), 8, N'ÁO THUN NAM ĐỎ SIZE M', N'M', N'1-THUNNAM-DO-M', CAST(0x0000ACFC00000000 AS DateTime), 1)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (3, N'Xanh', CAST(0x0000ACFC00000000 AS DateTime), 9, N'ÁO THUN NAM XANH SIZE M', N'M', N'1-THUNNAM-XA-S', CAST(0x0000ACFC00000000 AS DateTime), 1)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (4, N'Xanh', CAST(0x0000ACFC00000000 AS DateTime), 10, N'ÁO THUN NAM XANH SIZE L', N'L', N'1-THUNNAM-XA-M', CAST(0x0000ACFC00000000 AS DateTime), 1)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (5, N'Đỏ', CAST(0x0000ACFC00000000 AS DateTime), 5, N'VÁY NỮ ĐỎ SIZE S', N'S', N'2-VAYNU-DO-S', CAST(0x0000ACFC00000000 AS DateTime), 2)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (6, N'Đỏ', CAST(0x0000ACFC00000000 AS DateTime), 6, N'VÁY NỮ ĐỎ SIZE M', N'M', N'2-VAYNU-DO-M', CAST(0x0000ACFC00000000 AS DateTime), 2)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (7, N'Đỏ', CAST(0x0000ACFC00000000 AS DateTime), 6, N'VÁY NỮ ĐỎ SIZE L', N'L', N'2-VAYNU-DO-L', CAST(0x0000ACFC00000000 AS DateTime), 2)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (8, N'Hồng', CAST(0x0000ACFC00000000 AS DateTime), 7, N'VÁY NỮ HỒNG SIZE S', N'S', N'2-VAYNU-HO-S', CAST(0x0000ACFC00000000 AS DateTime), 2)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (9, N'Hồng', CAST(0x0000ACFC00000000 AS DateTime), 7, N'VÁY NỮ HỒNG SIZE M', N'M', N'2-VAYNU-HO-M', CAST(0x0000ACFC00000000 AS DateTime), 2)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (10, N'Hồng', CAST(0x0000ACFC00000000 AS DateTime), 8, N'VÁY NỮ HỒNG SIZE L', N'L', N'2-VAYNU-HO-L', CAST(0x0000ACFC00000000 AS DateTime), 2)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (11, N'ĐEN', CAST(0x0000ACFC00000000 AS DateTime), 5, N'QUẦN DÀI NAM ĐEN SIZE S', N'S', N'3-QUANNAM-DEN-S', CAST(0x0000ACFC00000000 AS DateTime), 3)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (12, N'ĐEN', CAST(0x0000ACFC00000000 AS DateTime), 6, N'QUẦN DÀI NAM ĐEN SIZE M', N'M', N'3-QUANNAM-DEN-M', CAST(0x0000ACFC00000000 AS DateTime), 3)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (13, N'ĐEN', CAST(0x0000ACFC00000000 AS DateTime), 7, N'QUẦN DÀI NAM ĐEN SIZE L', N'L', N'3-QUANNAM-DEN-L', CAST(0x0000ACFC00000000 AS DateTime), 3)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (14, N'NÂU', CAST(0x0000ACFC00000000 AS DateTime), 8, N'QUẦN DÀI NAM NÂU SIZE S', N'S', N'3-QUANNAM-NAU-S', CAST(0x0000ACFC00000000 AS DateTime), 3)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (15, N'NÂU', CAST(0x0000ACFC00000000 AS DateTime), 9, N'QUẦN DÀI NAM NÂU SIZE M', N'M', N'3-QUANNAM-NAU-M', CAST(0x0000ACFC00000000 AS DateTime), 3)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (16, N'NÂU', CAST(0x0000ACFC00000000 AS DateTime), 4, N'QUẦN DÀI NAM NÂU SIZE L', N'L', N'3-QUANNAM-NAU-L', CAST(0x0000ACFC00000000 AS DateTime), 3)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (17, N'Đỏ', CAST(0x0000ACFC00000000 AS DateTime), 0, N'ÁO THUN NAM ĐỎ SIZE L', N'L', N'1-THUNNAM-DO-L', CAST(0x0000ACFC00000000 AS DateTime), 1)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [sku], [updated_at], [product_id]) VALUES (18, N'Xanh', CAST(0x0000ACFC00000000 AS DateTime), 0, N'ÁO THUN NAM XANH SIZE S', N'S', N'1-THUNNAM-XA-S', CAST(0x0000ACFC00000000 AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[SubProduct] OFF
SET IDENTITY_INSERT [dbo].[Supplier] ON 

INSERT [dbo].[Supplier] ([id], [address], [email], [name], [phone]) VALUES (1, N'Số 129+131 Lê Thanh Nghị, Hai Bà Trưng, Hà Nội', N'tuyetanh173@gmail.com', N'Công ty Cổ Phần May mặc Việt Tiến', N'0909273402')
SET IDENTITY_INSERT [dbo].[Supplier] OFF
SET ANSI_PADDING ON

GO
/****** Object:  Index [UK_de34gsw4qt8auhffbna969ahp]    Script Date: 04/19/2021 07:48:55 ******/
ALTER TABLE [dbo].[Account] ADD  CONSTRAINT [UK_de34gsw4qt8auhffbna969ahp] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UK_3qgg01qojcmbdp47dkaom9x45]    Script Date: 04/19/2021 07:48:55 ******/
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
