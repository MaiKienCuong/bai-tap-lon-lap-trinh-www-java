USE [QuanAoTreEm]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 05/06/2021 12:23:42 ******/
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
/****** Object:  Table [dbo].[Account_Role]    Script Date: 05/06/2021 12:23:42 ******/
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
/****** Object:  Table [dbo].[Category]    Script Date: 05/06/2021 12:23:42 ******/
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
/****** Object:  Table [dbo].[Customer]    Script Date: 05/06/2021 12:23:42 ******/
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
/****** Object:  Table [dbo].[ImagesUrl]    Script Date: 05/06/2021 12:23:42 ******/
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
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 05/06/2021 12:23:42 ******/
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
/****** Object:  Table [dbo].[Orders]    Script Date: 05/06/2021 12:23:42 ******/
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
/****** Object:  Table [dbo].[Product]    Script Date: 05/06/2021 12:23:42 ******/
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
/****** Object:  Table [dbo].[Roles]    Script Date: 05/06/2021 12:23:42 ******/
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
/****** Object:  Table [dbo].[SubProduct]    Script Date: 05/06/2021 12:23:42 ******/
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
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Supplier]    Script Date: 05/06/2021 12:23:42 ******/
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

INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (1, 1, N'$2y$12$tdJg1zlfvDA6cgWUp2iIFONrF5JRwlbtjobysRhR7GnIMuxLnp1Me', N'customer1')
INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (2, 1, N'$2y$12$PPl1PLizsFGUd3B1tG/STejbtyXh.7zvFz9WwMieA8V57i0NOfz0C', N'customer2')
INSERT [dbo].[Account] ([id], [enable], [password], [username]) VALUES (3, 1, N'$2y$12$/dEXlyaWWptJrJJpJzD2N.UFgz/p8Ga9feaoGvmbphruMNz20r2km', N'admin')
SET IDENTITY_INSERT [dbo].[Account] OFF
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (1, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (2, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (3, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (3, 2)
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
SET IDENTITY_INSERT [dbo].[Category] OFF
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([id], [address], [email], [name], [phone], [account_id]) VALUES (1, N'15 Nguyễn Thái Sơn Quận Gò Vấp Thành Hồ Chí Minh', N'nguyenlananh15@gmail.com', N'Nguyễn Lan Anh', N'0961514852', 1)
INSERT [dbo].[Customer] ([id], [address], [email], [name], [phone], [account_id]) VALUES (2, N'20 Phan Văn Trị Quận Gò Vấp Thành Hồ Chí Minh', N'nguyenvanminh@gmail.com', N'Nguyễn Văn Minh', N'0976543672', 2)
SET IDENTITY_INSERT [dbo].[Customer] OFF
SET IDENTITY_INSERT [dbo].[OrderDetail] ON 

INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (1, 200000, 1, 1, 1)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (2, 250000, 1, 1, 7)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (3, 220000, 1, 1, 13)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (4, 220000, 1, 1, 22)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (5, 220000, 1, 1, 33)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (6, 200000, 1, 2, 2)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (7, 250000, 1, 2, 8)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (8, 220000, 1, 2, 14)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (9, 220000, 1, 2, 23)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (10, 250000, 1, 2, 40)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (11, 200000, 1, 3, 3)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (12, 250000, 1, 3, 9)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (13, 220000, 1, 3, 15)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (14, 220000, 1, 3, 24)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (15, 250000, 1, 3, 35)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (16, 200000, 1, 4, 4)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (17, 250000, 1, 4, 10)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (18, 220000, 1, 4, 16)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (19, 220000, 1, 4, 25)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (20, 250000, 1, 4, 36)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (21, 200000, 1, 5, 5)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (22, 250000, 1, 5, 11)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (23, 220000, 1, 5, 17)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (24, 220000, 1, 5, 26)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (25, 250000, 1, 5, 37)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (26, 200000, 1, 6, 6)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (27, 250000, 1, 6, 12)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (28, 220000, 1, 6, 18)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (29, 220000, 1, 6, 27)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (30, 250000, 1, 6, 38)
SET IDENTITY_INSERT [dbo].[OrderDetail] OFF
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (1, CAST(0x0000AD2000B4223F AS DateTime), N'COD', N'15 Nguyễn Thái Sơn Quận Gò Vấp Thành Hồ Chí Minh', N'PENDING', 1110000, 1)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (2, CAST(0x0000AD2000B4223F AS DateTime), N'COD', N'15 Nguyễn Thái Sơn Quận Gò Vấp Thành Hồ Chí Minh', N'PROCESSING', 1140000, 1)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (3, CAST(0x0000AD2000B4223F AS DateTime), N'STORE', N'15 Nguyễn Thái Sơn Quận Gò Vấp Thành Hồ Chí Minh', N'COMPLETED', 1140000, 1)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (4, CAST(0x0000AD2000B42242 AS DateTime), N'COD', N'20 Phan Văn Trị Quận Gò Vấp Thành Hồ Chí Minh', N'PENDING', 1140000, 2)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (5, CAST(0x0000AD2000B42242 AS DateTime), N'COD', N'20 Phan Văn Trị Quận Gò Vấp Thành Hồ Chí Minh', N'PROCESSING', 1140000, 2)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (6, CAST(0x0000AD2000B42242 AS DateTime), N'STORE', N'20 Phan Văn Trị Quận Gò Vấp Thành Hồ Chí Minh', N'COMPLETED', 1140000, 2)
SET IDENTITY_INSERT [dbo].[Orders] OFF
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (1, 1, CAST(0x0000AD2000ACD10A AS DateTime), 0, N'Quần jeans chắc chắn là món đồ bé nào cũng phải có với sự tiện lợi và dễ mặc. Chất liệu thun co giãn nhẹ mang đến cho bé sự thoải mái hết cỡ khi mặc. Quần có túi wash rách nhẹ, mặc ngắn trên mắc cá chân.sành điệu.', N'', N'Jean thun co giãn ', N'Quần jeans dài cho bé trai từ 1 - 6 tuổi', N'Hồ Chí Minh Việt Nam', 200000, N'Quần jeans dài cho bé trai từ 1 - 6 tuổi mộc trơn wash rách nhẹ sành điệu, 2 màu trắng đen cơ bản cho bé lựa chọn dễ mix đồ.', 0, CAST(0x0000AD2000ACD10A AS DateTime), 0, 1, 1)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (2, 1, CAST(0x0000AD2000ACD10A AS DateTime), 0, N'Quần jeans chắc chắn là món đồ bé nào cũng phải có với sự tiện lợi và dễ mặc. Chất liệu thun co giãn nhẹ mang đến cho bé sự thoải mái hết cỡ khi mặc. Quần có túi wash rách nhẹ, mặc ngắn trên mắc cá chân.sành điệu.', N'', N'Jean thun co giãn ', N'Quần jeans dài cho bé trai từ 6-10 tuổi', N'Hồ Chí Minh Việt Nam', 250000, N'Quần jeans dài cho bé trai từ 6-10 tuổi mộc trơn wash rách nhẹ sành điệu, 2 màu trắng đen cơ bản cho bé lựa chọn dễ mix đồ.', 0, CAST(0x0000AD2000ACD10A AS DateTime), 0, 1, 1)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (3, 1, CAST(0x0000AD2000ACD10A AS DateTime), 0, N'Quần thể thao cho bé 3 - 9 tuổi dài lưng thun mix sọc màu trơn, bé mặc cá tính, năng động. Được may bằng chất vải thun da cá dày dặn, dễ thấm hút mồ hôi.', N'HOT', N'100% cotton thấm hút mồ hôi', N'Quần thể thao cho bé 3 - 9 tuổi', N'Hồ Chí Minh Việt Nam', 220000, N'Quần thể thao cho bé 3 - 9 tuổi dài lưng thun mix sọc màu trơn, bé mặc cá tính, năng động.', 0, CAST(0x0000AD2000ACD10A AS DateTime), 0, 7, 1)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (4, 1, CAST(0x0000AD2000ACD10A AS DateTime), 0, N'Quần short màu trơn, có dây rút, quần măc ôm vừa, lai quần xẻ nhẹ sành điệu. Kết hợp quần với áo thun hay áo kiểu cho bé mặc đi chơi vừa thoải mái lại rất thời trang', N'HOT', N'100% cotton thấm hút mồ hôi', N'Quần short thể thao cho bé trai 9 tuổi đến 12 tuổi', N'Hồ Chí Minh Việt Nam', 220000, N'Quần short thể thao cho bé trai 9 tuổi đến 12 tuổi , thời trang hè cho bé mặc tiện lợi, mát mẻ, năng động', 0, CAST(0x0000AD2000ACD10A AS DateTime), 0, 7, 1)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (5, 1, CAST(0x0000AD2000ACD10A AS DateTime), 0, N'Áo khoác cho bé trai được làm từ chất liệu Kaki mềm, mịn mang lại sự sang trọng và cảm giác dễ chịu cho làn da em bé.', N'HOT', N'Vải bền đẹp, giặt không ra màu, không bong tróc,phù hợp 10-16 tuổi', N'Áo khoác kaki cho bé trai 2 lớp', N'Hồ Chí Minh Việt Nam', 220000, N'Áo khoác cho bé trai được làm từ chất liệu Kaki mềm, mịn mang lại sự sang trọng và cảm giác dễ chịu cho làn da em bé.', 0, CAST(0x0000AD2000ACD10A AS DateTime), 0, 9, 1)
INSERT [dbo].[Product] ([id], [active], [created_at], [discount], [long_description], [marker], [material], [name], [origin], [price], [short_description], [tax], [updated_at], [views], [category_id], [supplier_id]) VALUES (6, 1, CAST(0x0000AD2000B2A415 AS DateTime), 0.1, N'Váy xòe chắc chắn là món đồ bé nào cũng phải có với sự tiện lợi và dễ mặc. Chất liệu thun co giãn nhẹ mang đến cho bé sự thoải mái hết cỡ khi mặc', N'DIS', N'100% cotton đem lại sự thoải mái cho bé khi mặc', N'Váy xòe cho bé từ 6-10 tuổi', N'Hà Nội Việt Nam', 300000, N'Váy xòe cho bé từ 6-10 tuổi thiết kế dươn giản sành điệu, có nhiều màu cho bé dễ phối đồ', 0, CAST(0x0000AD2000B2A415 AS DateTime), 0, 13, 2)
SET IDENTITY_INSERT [dbo].[Product] OFF
SET IDENTITY_INSERT [dbo].[Roles] ON 

INSERT [dbo].[Roles] ([id], [role]) VALUES (1, N'ROLE_CUSTOMER')
INSERT [dbo].[Roles] ([id], [role]) VALUES (2, N'ROLE_ADMIN')
SET IDENTITY_INSERT [dbo].[Roles] OFF
SET IDENTITY_INSERT [dbo].[SubProduct] ON 

INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (1, N'Xanh', CAST(0x0000AD2000ADEC71 AS DateTime), 10, N'Quần jeans dài cho bé trai từ 1 - 6 tuổi màu Xanh size S', N'S', CAST(0x0000AD2000ADEC71 AS DateTime), 1)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (2, N'Xanh', CAST(0x0000AD2000ADEC71 AS DateTime), 10, N'Quần jeans dài cho bé trai từ 1 - 6 tuổi màu Xanh size M', N'M', CAST(0x0000AD2000ADEC71 AS DateTime), 1)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (3, N'Xanh', CAST(0x0000AD2000ADEC71 AS DateTime), 10, N'Quần jeans dài cho bé trai từ 1 - 6 tuổi màu Xanh size L', N'L', CAST(0x0000AD2000ADEC71 AS DateTime), 1)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (4, N'Đen', CAST(0x0000AD2000ADEC71 AS DateTime), 10, N'Quần jeans dài cho bé trai từ 1 - 6 tuổi màu Đen size S', N'S', CAST(0x0000AD2000ADEC71 AS DateTime), 1)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (5, N'Đen', CAST(0x0000AD2000ADEC71 AS DateTime), 10, N'Quần jeans dài cho bé trai từ 1 - 6 tuổi màu Đen size M', N'M', CAST(0x0000AD2000ADEC71 AS DateTime), 1)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (6, N'Đen', CAST(0x0000AD2000ADEC71 AS DateTime), 10, N'Quần jeans dài cho bé trai từ 1 - 6 tuổi màu Đen size L', N'L', CAST(0x0000AD2000ADEC71 AS DateTime), 1)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (7, N'Xanh', CAST(0x0000AD2000AE370C AS DateTime), 10, N'Quần jeans dài cho bé trai từ 6-10 tuổi màu Xanh size S', N'S', CAST(0x0000AD2000AE370C AS DateTime), 2)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (8, N'Xanh', CAST(0x0000AD2000AE370C AS DateTime), 10, N'Quần jeans dài cho bé trai từ 6-10 tuổi màu Xanh size M', N'M', CAST(0x0000AD2000AE370C AS DateTime), 2)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (9, N'Xanh', CAST(0x0000AD2000AE370C AS DateTime), 10, N'Quần jeans dài cho bé trai từ 6-10 tuổi màu Xanh size L', N'L', CAST(0x0000AD2000AE370C AS DateTime), 2)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (10, N'Đen', CAST(0x0000AD2000AE370C AS DateTime), 10, N'Quần jeans dài cho bé trai từ 6-10 tuổi màu Đen size S', N'S', CAST(0x0000AD2000AE370C AS DateTime), 2)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (11, N'Đen', CAST(0x0000AD2000AE370C AS DateTime), 10, N'Quần jeans dài cho bé trai từ 6-10 tuổi màu Đen size M', N'M', CAST(0x0000AD2000AE370C AS DateTime), 2)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (12, N'Đen', CAST(0x0000AD2000AE370C AS DateTime), 10, N'Quần jeans dài cho bé trai từ 6-10 tuổi màu Đen size L', N'L', CAST(0x0000AD2000AE370C AS DateTime), 2)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (13, N'Xanh ngọc', CAST(0x0000AD2000AEA06F AS DateTime), 10, N'Quần thể thao cho bé 3 - 9 tuổi màu Xanh ngọc size S', N'S', CAST(0x0000AD2000AEA06F AS DateTime), 3)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (14, N'Xanh ngọc', CAST(0x0000AD2000AEA06F AS DateTime), 10, N'Quần thể thao cho bé 3 - 9 tuổi màu Xanh ngọc size M', N'M', CAST(0x0000AD2000AEA06F AS DateTime), 3)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (15, N'Xanh ngọc', CAST(0x0000AD2000AEA06F AS DateTime), 10, N'Quần thể thao cho bé 3 - 9 tuổi màu Xanh ngọc size L', N'L', CAST(0x0000AD2000AEA06F AS DateTime), 3)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (16, N'Xám', CAST(0x0000AD2000AEA06F AS DateTime), 10, N'Quần thể thao cho bé 3 - 9 tuổi màu Xám size S', N'S', CAST(0x0000AD2000AEA06F AS DateTime), 3)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (17, N'Xám', CAST(0x0000AD2000AEA06F AS DateTime), 10, N'Quần thể thao cho bé 3 - 9 tuổi màu Xám size M', N'M', CAST(0x0000AD2000AEA06F AS DateTime), 3)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (18, N'Xám', CAST(0x0000AD2000AEA06F AS DateTime), 10, N'Quần thể thao cho bé 3 - 9 tuổi màu Xám size L', N'L', CAST(0x0000AD2000AEA06F AS DateTime), 3)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (19, N'Xanh dương', CAST(0x0000AD2000AF6B16 AS DateTime), 15, N'Quần short thể thao cho bé trai 9 tuổi đến 12 tuổi màu Xanh dương size S', N'S', CAST(0x0000AD2000AF6B16 AS DateTime), 4)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (20, N'Xanh dương', CAST(0x0000AD2000AF6B16 AS DateTime), 15, N'Quần short thể thao cho bé trai 9 tuổi đến 12 tuổi màu Xanh dương size M', N'M', CAST(0x0000AD2000AF6B16 AS DateTime), 4)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (21, N'Xám gray', CAST(0x0000AD2000AF6B16 AS DateTime), 15, N'Quần short thể thao cho bé trai 9 tuổi đến 12 tuổi màu Xám gray size S', N'S', CAST(0x0000AD2000AF6B16 AS DateTime), 4)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (22, N'Xám gray', CAST(0x0000AD2000AF6B16 AS DateTime), 15, N'Quần short thể thao cho bé trai 9 tuổi đến 12 tuổi màu Xám gray size M', N'M', CAST(0x0000AD2000AF6B16 AS DateTime), 4)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (23, N'Nâu đất', CAST(0x0000AD2000AF6B16 AS DateTime), 15, N'Quần short thể thao cho bé trai 9 tuổi đến 12 tuổi màu Nâu đất size S', N'S', CAST(0x0000AD2000AF6B16 AS DateTime), 4)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (24, N'Nâu đất', CAST(0x0000AD2000AF6B16 AS DateTime), 15, N'Quần short thể thao cho bé trai 9 tuổi đến 12 tuổi màu Nâu đất size M', N'M', CAST(0x0000AD2000AF6B16 AS DateTime), 4)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (25, N'Xanh đen', CAST(0x0000AD2000B0E75A AS DateTime), 12, N'Áo khoác kaki cho bé trai 2 lớp màu Xanh đen size S', N'S', CAST(0x0000AD2000B0E75A AS DateTime), 5)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (26, N'Xanh đen', CAST(0x0000AD2000B0E75A AS DateTime), 12, N'Áo khoác kaki cho bé trai 2 lớp màu Xanh đen size M', N'M', CAST(0x0000AD2000B0E75A AS DateTime), 5)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (27, N'Xanh đen', CAST(0x0000AD2000B0E75A AS DateTime), 12, N'Áo khoác kaki cho bé trai 2 lớp màu Xanh đen size L', N'L', CAST(0x0000AD2000B0E75A AS DateTime), 5)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (28, N'Đỏ đen', CAST(0x0000AD2000B0E75A AS DateTime), 14, N'Áo khoác kaki cho bé trai 2 lớp màu Đỏ đen size S', N'S', CAST(0x0000AD2000B0E75A AS DateTime), 5)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (29, N'Đỏ đen', CAST(0x0000AD2000B0E75A AS DateTime), 14, N'Áo khoác kaki cho bé trai 2 lớp màu Đỏ đen size M', N'M', CAST(0x0000AD2000B0E75A AS DateTime), 5)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (30, N'Đỏ đen', CAST(0x0000AD2000B0E75A AS DateTime), 14, N'Áo khoác kaki cho bé trai 2 lớp màu Đỏ đen size L', N'L', CAST(0x0000AD2000B0E75A AS DateTime), 5)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (31, N'Cam', CAST(0x0000AD2000B0E75A AS DateTime), 15, N'Áo khoác kaki cho bé trai 2 lớp màu Cam size S', N'S', CAST(0x0000AD2000B0E75A AS DateTime), 5)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (32, N'Cam', CAST(0x0000AD2000B0E75A AS DateTime), 15, N'Áo khoác kaki cho bé trai 2 lớp màu Cam size M', N'M', CAST(0x0000AD2000B0E75A AS DateTime), 5)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (33, N'Cam', CAST(0x0000AD2000B0E75A AS DateTime), 15, N'Áo khoác kaki cho bé trai 2 lớp màu Cam size L', N'L', CAST(0x0000AD2000B0E75A AS DateTime), 5)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (34, N'Hồng', CAST(0x0000AD2000B34E98 AS DateTime), 8, N'Váy xòe cho bé từ 6-10 tuổi màu Hồng size S', N'S', CAST(0x0000AD2000B34E98 AS DateTime), 6)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (35, N'Hồng', CAST(0x0000AD2000B34E98 AS DateTime), 8, N'Váy xòe cho bé từ 6-10 tuổi màu Hồng size M', N'M', CAST(0x0000AD2000B34E98 AS DateTime), 6)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (36, N'Hồng', CAST(0x0000AD2000B34E98 AS DateTime), 8, N'Váy xòe cho bé từ 6-10 tuổi màu Hồng size L', N'L', CAST(0x0000AD2000B34E98 AS DateTime), 6)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (37, N'Đỏ', CAST(0x0000AD2000B34E98 AS DateTime), 9, N'Váy xòe cho bé từ 6-10 tuổi màu Đỏ size S', N'S', CAST(0x0000AD2000B34E98 AS DateTime), 6)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (38, N'Đỏ', CAST(0x0000AD2000B34E98 AS DateTime), 9, N'Váy xòe cho bé từ 6-10 tuổi màu Đỏ size M', N'M', CAST(0x0000AD2000B34E98 AS DateTime), 6)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (39, N'Đỏ', CAST(0x0000AD2000B34E98 AS DateTime), 9, N'Váy xòe cho bé từ 6-10 tuổi màu Đỏ size L', N'L', CAST(0x0000AD2000B34E98 AS DateTime), 6)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (40, N'Trắng', CAST(0x0000AD2000B34E98 AS DateTime), 10, N'Váy xòe cho bé từ 6-10 tuổi màu Trắng size S', N'S', CAST(0x0000AD2000B34E98 AS DateTime), 6)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (41, N'Trắng', CAST(0x0000AD2000B34E98 AS DateTime), 10, N'Váy xòe cho bé từ 6-10 tuổi màu Trắng size M', N'M', CAST(0x0000AD2000B34E98 AS DateTime), 6)
INSERT [dbo].[SubProduct] ([id], [color], [created_at], [inventory], [name], [size], [updated_at], [product_id]) VALUES (42, N'Trắng', CAST(0x0000AD2000B34E98 AS DateTime), 10, N'Váy xòe cho bé từ 6-10 tuổi màu Trắng size L', N'L', CAST(0x0000AD2000B34E98 AS DateTime), 6)
SET IDENTITY_INSERT [dbo].[SubProduct] OFF
SET IDENTITY_INSERT [dbo].[Supplier] ON 

INSERT [dbo].[Supplier] ([id], [address], [email], [name], [phone]) VALUES (1, N'Số 129+131 Lê Thanh Nghị, Hai Bà Trưng, Hà Nội', N'tuyetanh173@gmail.com', N'Công ty Cổ Phần May mặc Việt Tiến', N'0909273402')
INSERT [dbo].[Supplier] ([id], [address], [email], [name], [phone]) VALUES (2, N'Số 129+131 Nguyễn Văn Bảo Quận Gò Vấp Thành phố Hồ Chí Minh', N'minhanhnguyen172@gmail.com', N'Công ty Cổ Phần May mặc Gia Định', N'0909273403')
SET IDENTITY_INSERT [dbo].[Supplier] OFF
SET ANSI_PADDING ON

GO
/****** Object:  Index [UK_de34gsw4qt8auhffbna969ahp]    Script Date: 05/06/2021 12:23:42 ******/
ALTER TABLE [dbo].[Account] ADD  CONSTRAINT [UK_de34gsw4qt8auhffbna969ahp] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UK_3qgg01qojcmbdp47dkaom9x45]    Script Date: 05/06/2021 12:23:42 ******/
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
