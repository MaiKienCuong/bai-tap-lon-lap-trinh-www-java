USE master
GO
CREATE DATABASE [QuanAoTreEm]
GO
USE [QuanAoTreEm]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 04/10/2021 07:06:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[email] [varchar](50) NOT NULL,
	[enable] [bit] NOT NULL,
	[password] [varchar](255) NOT NULL,
	[username] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Account_Role]    Script Date: 04/10/2021 07:06:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account_Role](
	[account_id] [numeric](19, 0) NOT NULL,
	[role_id] [numeric](19, 0) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[account_id] ASC,
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 04/10/2021 07:06:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[address] [nvarchar](255) NULL,
	[name] [nvarchar](255) NOT NULL,
	[phone] [varchar](50) NULL,
	[account_id] [numeric](19, 0) NULL,
	[typeCustomer_id] [numeric](19, 0) NULL,
	[email] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 04/10/2021 07:06:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[price] [numeric](19, 2) NOT NULL,
	[quantity] [int] NOT NULL,
	[order_id] [numeric](19, 0) NULL,
	[subProduct_id] [numeric](19, 0) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 04/10/2021 07:06:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[order_date] [datetime] NULL,
	[payment_method] [nvarchar](255) NULL,
	[payment_status] [nvarchar](255) NULL,
	[ship_address] [nvarchar](255) NULL,
	[shipped_date] [datetime] NULL,
	[status] [nvarchar](255) NULL,
	[total] [numeric](19, 2) NULL,
	[customer_id] [numeric](19, 0) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 04/10/2021 07:06:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[active] [bit] NOT NULL,
	[category] [nvarchar](255) NULL,
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
	[url] [varchar](255) NULL,
	[views] [int] NULL,
	[supplier_id] [numeric](19, 0) NULL,
	[created_at] [datetime] NULL,
	[updated_at] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 04/10/2021 07:06:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[role] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SubProduct]    Script Date: 04/10/2021 07:06:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SubProduct](
	[id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[created_at] [datetime] NULL,
	[color] [nvarchar](50) NULL,
	[inventory] [int] NULL,
	[size] [nvarchar](50) NULL,
	[name] [nvarchar](255) NULL,
	[sku] [varchar](50) NULL,
	[updated_at] [datetime] NULL,
	[product_id] [numeric](19, 0) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Supplier]    Script Date: 04/10/2021 07:06:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Supplier](
	[id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
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
/****** Object:  Table [dbo].[TypeCustomer]    Script Date: 04/10/2021 07:06:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TypeCustomer](
	[id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[preferential] [float] NULL,
	[type] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 04/10/2021 07:06:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[address] [nvarchar](255) NULL,
	[birthDate] [datetime] NULL,
	[identity_card] [varchar](50) NULL,
	[name] [nvarchar](255) NOT NULL,
	[native_country] [nvarchar](255) NULL,
	[phone] [varchar](50) NULL,
	[account_id] [numeric](19, 0) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (CAST(4 AS Numeric(19, 0)), N'mai@gmail.com', 1, N'$2a$10$jnlqLzexNHiUiznMidz1ue9PXpAdcwroUwM/LrL/3CMSc6vSii22i', N'mai')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (CAST(5 AS Numeric(19, 0)), N'kien@gmail.com', 1, N'$2a$10$XQ4aOQenrusSUzVyQzUCKOCmRVkaptEpbfA66t6kV/p6y8ufLRJf.', N'kien')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (CAST(6 AS Numeric(19, 0)), N'cuong@gmail.com', 1, N'$2a$10$C6cCHmVzUizRs/5FfeRLouZPmuYp4L5XN4yl/Sb79h0A6/2TtGwg6', N'cuong')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (CAST(14 AS Numeric(19, 0)), N'kiencuong@gmail.com', 1, N'$2a$10$HT3YlOvx9mb7w5XCLoMH7.QWo8dh2cz61ayj8uVJWGKboV9R8cofy', N'kiencuong')
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (CAST(4 AS Numeric(19, 0)), CAST(2 AS Numeric(19, 0)))
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (CAST(5 AS Numeric(19, 0)), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (CAST(5 AS Numeric(19, 0)), CAST(2 AS Numeric(19, 0)))
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (CAST(6 AS Numeric(19, 0)), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (CAST(6 AS Numeric(19, 0)), CAST(2 AS Numeric(19, 0)))
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (CAST(6 AS Numeric(19, 0)), CAST(3 AS Numeric(19, 0)))
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (CAST(14 AS Numeric(19, 0)), CAST(4 AS Numeric(19, 0)))
GO
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([id], [address], [name], [phone], [account_id], [typeCustomer_id], [email]) VALUES (CAST(2 AS Numeric(19, 0)), NULL, N'Mai Kiên Cường', N'0961516941', CAST(14 AS Numeric(19, 0)), CAST(1 AS Numeric(19, 0)), N'kiencuong@gmail.com')
SET IDENTITY_INSERT [dbo].[Customer] OFF
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([id], [active], [category], [discount], [images_folder], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [url], [views], [supplier_id], [created_at], [updated_at]) VALUES (CAST(1 AS Numeric(19, 0)), 1, N'ÁO THUN NAM TRẺ EM', 0, N'folder1', N'áo thun nam khủng long dành cho trẻ từ 5-6 tuổi, hợp thời trang, chất liệu cotton thoáng mát, thấm hút mồ hôi, mềm mại với da', N'HOT', N'100% cotton', N'ÁO THUN NAM KHỦNG LONG', N'Hồ Chí Minh Việt Nam', CAST(250000.00 AS Numeric(19, 2)), N'áo thun nam khủng long dành cho trẻ từ 5-6 tuổi, hợp thời trang, chất liệu cotton thoáng mát, thấm hút mồ hôi, mềm mại với da', 0, N'img1.jpg', 123, CAST(1 AS Numeric(19, 0)), CAST(N'2021-04-10T00:00:00.000' AS DateTime), CAST(N'2021-04-10T00:00:00.000' AS DateTime))
INSERT [dbo].[Product] ([id], [active], [category], [discount], [images_folder], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [url], [views], [supplier_id], [created_at], [updated_at]) VALUES (CAST(2 AS Numeric(19, 0)), 1, N'VÁY TRẺ EM', 0.05, N'folder2', N'váy nữ dành cho trẻ từ 5-6 tuổi, chất liệu thoáng mát, mềm mại với da', N'DIS', N'100% cotton', N'VÁY NỮ', N'Hồ Chí Minh Việt Nam', CAST(300000.00 AS Numeric(19, 2)), N'váy nữ dành cho trẻ từ 5-6 tuổi, chất liệu thoáng mát, mềm mại với da', 0, N'img2.jpg', 100, CAST(1 AS Numeric(19, 0)), CAST(N'2021-04-09T00:00:00.000' AS DateTime), CAST(N'2021-04-09T00:00:00.000' AS DateTime))
INSERT [dbo].[Product] ([id], [active], [category], [discount], [images_folder], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [url], [views], [supplier_id], [created_at], [updated_at]) VALUES (CAST(3 AS Numeric(19, 0)), 1, N'QUẦN DÀI NAM TRẺ EM', 0, N'folder3', N'quần dài dành cho trẻ từ 5-6 tuổi, chất liệu thoáng mát, mềm mại với da', N'DEF', N'100% cotton', N'QUẦN DÀI TRẺ NAM', N'Hồ Chí Minh Việt Nam', CAST(29000.00 AS Numeric(19, 2)), N'quần dài dành cho trẻ từ 5-6 tuổi, chất liệu thoáng mát, mềm mại với da', 0, N'img3.jpg', 200, CAST(1 AS Numeric(19, 0)), CAST(N'2021-04-08T00:00:00.000' AS DateTime), CAST(N'2021-04-08T00:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([id], [role]) VALUES (CAST(1 AS Numeric(19, 0)), N'ROLE_MANAGER')
INSERT [dbo].[Role] ([id], [role]) VALUES (CAST(2 AS Numeric(19, 0)), N'ROLE_USER')
INSERT [dbo].[Role] ([id], [role]) VALUES (CAST(3 AS Numeric(19, 0)), N'ROLE_ADMIN')
INSERT [dbo].[Role] ([id], [role]) VALUES (CAST(4 AS Numeric(19, 0)), N'ROLE_CUSTOMER')
SET IDENTITY_INSERT [dbo].[Role] OFF
GO
SET IDENTITY_INSERT [dbo].[SubProduct] ON 

INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(1 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'Đỏ', 7, N'S', N'ÁO THUN NAM ĐỎ SIZE S', N'1-THUNNAM-DO-S', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(2 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'Đỏ', 8, N'M', N'ÁO THUN NAM ĐỎ SIZE M', N'1-THUNNAM-DO-M', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(3 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'Xanh', 9, N'M', N'ÁO THUN NAM XANH SIZE M', N'1-THUNNAM-XA-S', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(4 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'Xanh', 10, N'L', N'ÁO THUN NAM XANH SIZE L', N'1-THUNNAM-XA-M', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(5 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'Đỏ', 5, N'S', N'VÁY NỮ ĐỎ SIZE S', N'2-VAYNU-DO-S', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(2 AS Numeric(19, 0)))
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(6 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'Đỏ', 6, N'M', N'VÁY NỮ ĐỎ SIZE M', N'2-VAYNU-DO-M', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(2 AS Numeric(19, 0)))
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(7 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'Đỏ', 6, N'L', N'VÁY NỮ ĐỎ SIZE L', N'2-VAYNU-DO-L', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(2 AS Numeric(19, 0)))
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(8 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'Hồng', 7, N'S', N'VÁY NỮ HỒNG SIZE S', N'2-VAYNU-HO-S', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(2 AS Numeric(19, 0)))
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(9 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'Hồng', 7, N'M', N'VÁY NỮ HỒNG SIZE M', N'2-VAYNU-HO-M', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(2 AS Numeric(19, 0)))
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(10 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'Hồng', 8, N'L', N'VÁY NỮ HỒNG SIZE L', N'2-VAYNU-HO-L', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(2 AS Numeric(19, 0)))
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(11 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'ĐEN', 5, N'S', N'QUẦN DÀI NAM ĐEN SIZE S', N'3-QUANNAM-DEN-S', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(3 AS Numeric(19, 0)))
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(12 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'ĐEN', 6, N'M', N'QUẦN DÀI NAM ĐEN SIZE M', N'3-QUANNAM-DEN-M', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(3 AS Numeric(19, 0)))
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(13 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'ĐEN', 7, N'L', N'QUẦN DÀI NAM ĐEN SIZE L', N'3-QUANNAM-DEN-L', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(3 AS Numeric(19, 0)))
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(14 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'NÂU', 8, N'S', N'QUẦN DÀI NAM NÂU SIZE S', N'3-QUANNAM-NAU-S', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(3 AS Numeric(19, 0)))
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(15 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'NÂU', 9, N'M', N'QUẦN DÀI NAM NÂU SIZE M', N'3-QUANNAM-NAU-M', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(3 AS Numeric(19, 0)))
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(16 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'NÂU', 4, N'L', N'QUẦN DÀI NAM NÂU SIZE L', N'3-QUANNAM-NAU-L', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(3 AS Numeric(19, 0)))
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(17 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'Đỏ', 0, N'L', N'ÁO THUN NAM ĐỎ SIZE L', N'1-THUNNAM-DO-L', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(1 AS Numeric(19, 0)))
INSERT [dbo].[SubProduct] ([id], [created_at], [color], [inventory], [size], [name], [sku], [updated_at], [product_id]) VALUES (CAST(18 AS Numeric(19, 0)), CAST(N'2021-03-31T00:00:00.000' AS DateTime), N'Xanh', 0, N'S', N'ÁO THUN NAM XANH SIZE S', N'1-THUNNAM-XA-S', CAST(N'2021-03-31T00:00:00.000' AS DateTime), CAST(1 AS Numeric(19, 0)))
SET IDENTITY_INSERT [dbo].[SubProduct] OFF
GO
SET IDENTITY_INSERT [dbo].[Supplier] ON 

INSERT [dbo].[Supplier] ([id], [address], [email], [name], [phone]) VALUES (CAST(1 AS Numeric(19, 0)), N'Số 129+131 Lê Thanh Nghị, Hai Bà Trưng, Hà Nội', N'tuyetanh173@gmail.com', N'Công ty Cổ Phần May mặc Việt Tiến', N'0909273402')
SET IDENTITY_INSERT [dbo].[Supplier] OFF
GO
SET IDENTITY_INSERT [dbo].[TypeCustomer] ON 

INSERT [dbo].[TypeCustomer] ([id], [preferential], [type]) VALUES (CAST(1 AS Numeric(19, 0)), 0, N'NONE')
INSERT [dbo].[TypeCustomer] ([id], [preferential], [type]) VALUES (CAST(2 AS Numeric(19, 0)), 200000, N'SILVER')
INSERT [dbo].[TypeCustomer] ([id], [preferential], [type]) VALUES (CAST(3 AS Numeric(19, 0)), 500000, N'GOLD')
INSERT [dbo].[TypeCustomer] ([id], [preferential], [type]) VALUES (CAST(4 AS Numeric(19, 0)), 1000000, N'DIAMOND')
SET IDENTITY_INSERT [dbo].[TypeCustomer] OFF
GO
ALTER TABLE [dbo].[Account_Role]  WITH CHECK ADD  CONSTRAINT [FKd71suikq4c5irpg6to4825elp] FOREIGN KEY([role_id])
REFERENCES [dbo].[Role] ([id])
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
ALTER TABLE [dbo].[SubProduct]  WITH CHECK ADD  CONSTRAINT [FK2889lqosdcs0q2nmoqie3upqg] FOREIGN KEY([product_id])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[SubProduct] CHECK CONSTRAINT [FK2889lqosdcs0q2nmoqie3upqg]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FKfergpmatx5y1kcpscy3ycfhvv] FOREIGN KEY([account_id])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FKfergpmatx5y1kcpscy3ycfhvv]
GO
