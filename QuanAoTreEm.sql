USE [QuanAoTreEm]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 04/16/2021 09:40:02 ******/
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
/****** Object:  Table [dbo].[Account_Role]    Script Date: 04/16/2021 09:40:02 ******/
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
/****** Object:  Table [dbo].[Category]    Script Date: 04/16/2021 09:40:02 ******/
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
/****** Object:  Table [dbo].[Customer]    Script Date: 04/16/2021 09:40:02 ******/
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
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 04/16/2021 09:40:02 ******/
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
/****** Object:  Table [dbo].[Orders]    Script Date: 04/16/2021 09:40:02 ******/
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
/****** Object:  Table [dbo].[Product]    Script Date: 04/16/2021 09:40:02 ******/
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
/****** Object:  Table [dbo].[Roles]    Script Date: 04/16/2021 09:40:02 ******/
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
/****** Object:  Table [dbo].[SubProduct]    Script Date: 04/16/2021 09:40:02 ******/
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
/****** Object:  Table [dbo].[Supplier]    Script Date: 04/16/2021 09:40:02 ******/
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
/****** Object:  Table [dbo].[TypeCustomer]    Script Date: 04/16/2021 09:40:02 ******/
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
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (25, N'nguyenvanminh@gmail.com', 1, N'$2a$10$nity4K.HiwrSrnP8RUkgkuYI8QJAgnyHhRnAKGbiUtuhSVaLAJ3D2', N'nguyenvanminh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (26, N'admin@gmail.com', 1, N'$2y$12$px.nlzUUGMBG0OmEoo41HuniLqo/Bnqr6GgnyKctfpWExtlEVqCk6', N'admin')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (27, N'nguyenvanminhh@gmail.com', 1, N'$2a$10$/t27zunjaKCZKgH.lii9h.xLdrfwmpZcculWRPmOzvQLLtOPqhX.e', N'nguyenvanminhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (28, N'nguyenvanminhhh@gmail.com', 1, N'$2a$10$YlZMM2cLhyuv6QFwXZ.EHuK19r.0bUbs3yZjhilBOU/Cw4m.L5yI.', N'nguyenvanminhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (29, N'anhanh@gmail.com', 1, N'$2a$10$KliDfOoHqxYxeQp3KbB0SO9Rgt3qGRc6sGHgM2DjtAHMVFuENeKzG', N'anh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (30, N'nguyenvanminhhhh@gmail.com', 1, N'$2a$10$vqE.ShzRA632TnwEUM/RZepjVSRhN.acIk6I3r5sUyCeg5M8jGFf6', N'nguyenvanminhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (31, N'nguyenvanminhhhhh@gmail.com', 1, N'$2a$10$C8OiUvNnx0e.j2t4miLBWe1M3lLQzYNMJVfViQUoklFPcLm007r7.', N'nguyenvanminhhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (32, N'nguyenvananhh@gmail.com', 1, N'$2a$10$uJ14ly/0VHOJCtOH8KEkc.hcgO9hHtF6XRHS9egOIZeJAndNDemmy', N'nguyenvananhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (33, N'nguyenvananhhh@gmail.com', 1, N'$2a$10$UHK6NyD1pA0uoEoCh5HF1OtStR1oruORs1kXFMArNve/gXJ.JxX5q', N'nguyenvananhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (34, N'nguyenvananhhhh@gmail.com', 1, N'$2a$10$i1ujzOBui772VzXdPuLumuKI7/uhtRXoCMvA2U/KZkYCqS9Db4j2G', N'nguyenvanahnhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (35, N'nguyenvananhhhhh@gmail.com', 1, N'$2a$10$lZY5hbvAvnZkqSTQ3j1AQO1UNo1ypkCOo5Isfhv3HUey9IFt5k4Za', N'nguyenvanahnhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (36, N'nguyenvananhhhhhh@gmail.com', 1, N'$2a$10$fqt8eBu9HuEZxfePxTJcG.sBbt2i95PgOdnpSLQ3kTVxAFXixVniu', N'nguyenvanhahnhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (37, N'nguyenvananhhhhhhh@gmail.com', 1, N'$2a$10$iU1Zx29iFVyO6ofOxazNYejnWFgnwmTZGocAqHMWtFtwN4uiImkKq', N'nguyenvanhhahnhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (38, N'nguyenvananhhhhhhhh@gmail.com', 1, N'$2a$10$peJetupcQhPR2ZyOAuCuD.POowbO3kzUXpU5uNXnWe8USRHDd7mw.', N'nguyenvanhhahhnhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (39, N'nguyenvananhhhhhhhhh@gmail.com', 1, N'$2a$10$aGGUSOs67h/d8ki1b0O/y.5ueKWJrdZgLbIK7BP0oFLIm3yflbfJa', N'nguyenvanhhahhnhhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (40, N'nguyenvananhhhhhhhhhh@gmail.com', 1, N'$2a$10$yCrFqHRLynBbdo5qLzGmqOwPzqTQXagibd/ye.sa7MVg9Wa0xEE5a', N'nguyenvanhhahhhnhhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (41, N'nguyenvananhhhhhhhhhhh@gmail.com', 1, N'$2a$10$GQhhheFVTIZn/8PHuUfwZOwE/oMyro94v2r89vnhCFE2F.alhemGe', N'nguyenvanhhahhhnhhhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (43, N'nguyenvananhhhhhfhhhhhh@gmail.com', 1, N'$2a$10$T5xsXar3dBaoyOqLbTLZYuRcang1hdgnkfI5QQyT3BcC9NuVzrTMW', N'nguyenvanhhafhhhnhhhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (44, N'nguyenvananhhhhhgfhhhhhh@gmail.com', 1, N'$2a$10$DxRWa2r5qJs0rp71eozFOuV.9CNBDvKiR6L4UwKM2AqwJGDxK33QC', N'nguyenvanhhafhghhnhhhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (45, N'nguyenvananhhhhhggfhhhhhh@gmail.com', 1, N'$2a$10$nFa8Sllo/FKqGfer3gySruMuoSBzbT3or/pCoRAmIJ52wK9vvN3yi', N'nguyenvanhhafghghhnhhhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (46, N'nguyenvananhhhhhggfhghhhhh@gmail.com', 1, N'$2a$10$jTR1aegJrpM/.QDsGdiBLeH65383KIwTPgL12ierXi75q2m3TgpLG', N'nguyenvanhhafghghghnhhhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (47, N'nguyenvananhhhhhggfhghhjhhhh@gmail.com', 1, N'$2a$10$PWuCWyU76X9uzemcL3xe0ODsyD.hJEP895uUm1unLKCRxmKAfuQJS', N'nguyenvanhhafghghghhnhhhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (48, N'nguyenvananhhhhhggfhghhjthhhh@gmail.com', 1, N'$2a$10$fRQpKZyxFa1aARuUQm4Jf.XbUgyWwBdoW2rkZdRMvPUHNGkp3army', N'nguyenvtanhhafghghghhnhhhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (49, N'nguyenvananhhhhhggfhghhpjthhhh@gmail.com', 1, N'$2a$10$xaM.pZMylzzkr4ZSYmNWDu0qJKBSfluxSutuScqO7BtIQKc9ZmTUm', N'nguyenvtanhhafghghpghhnhhhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (50, N'nguyenvananhhhhhggfhghhjpjthhhh@gmail.com', 1, N'$2a$10$JUbmaXwdKtL4AIY3jonpwO5cLohK2Vhe3PB9LejvYpvLnqvqVn8A6', N'nguyenvtanhhafghghpghhjnhhhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (51, N'nguyenvananhhhhhggfhghhhjpjthhhh@gmail.com', 1, N'$2a$10$.SPdZGA4xW/a8AZvHigEhO2A4LJAz.kcKuElOk2FArBs7uHytEabK', N'nguyenvtanhhafghghpghhhjnhhhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (52, N'nguyenvananhhhhhggfhghhhjpjtjhhhh@gmail.com', 1, N'$2a$10$u.2b2QSfmB5//EUH8RvroeVeyTxw3xDWP7rHyg7yHG8nfnB2AJQOO', N'nguyenvtanhhafghghpghhhjnhhjhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (53, N'nguyenvananhhhhhggfhghhhjpfjtjhhhh@gmail.com', 1, N'$2a$10$yUSMTgQBR8Mcc8bOCtaiJ.vAv9zW2h0FmG0Ac5NFkxmSU9Za9LfDW', N'nguyenvtanhhafghghpghhhjfnhhjhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (54, N'nguyenvananhhhhhggfhghhhjpfjtjhhhhh@gmail.com', 1, N'$2a$10$BV7X8UlcBzneqjSPUUaoS.m0zaAxkgZafkbqO8e4OUuaLg/pm1I9W', N'nguyenvtanhhafghghpghhhjfnhhhjhhhh')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (55, N'@gmail.com', 1, N'$2a$10$aZhs7IsN.7o1Q.MDmqdN5O/zfy2mpkJd5Hqsd/J3UZebjv7X7Nm.y', N'')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (56, N'1@gmail.com', 1, N'$2a$10$I4n5LqKLv8/P9c/Dm7IX5.ZW2M8uGzdlaFI7GjkdvlC9AKKxCwXJS', N'1')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (57, N'1n@gmail.com', 1, N'$2a$10$.VWHynEmEJ66W95QD/dPFeAeUCAIucZTwMNxH3cg4UqUpE03nRIE.', N'1n')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (58, N'1hn@gmail.com', 1, N'$2a$10$BwjzLrFYXLKhNxP3ejQTqOm1gpLzgnKNItBxrvxExX15doyycVfgi', N'1hn')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (59, N'1hgn@gmail.com', 1, N'$2a$10$kJfhEOuekWM5YtgZLamgQuriv6q7CbpggfjQsvcXY6Ye9GyTVfTzC', N'1hng')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (60, N'1hdgn@gmail.com', 1, N'$2a$10$QE4E72tMW0z8He6JIfqj3.IVqkz6edjditrrHEu8ytPS8khmNo0JS', N'1hndg')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (61, N'1hdghn@gmail.com', 1, N'$2a$10$WDhzFxMjv8mJ8jeRP2Tzm.sT7CBfxQv3Xo22R0QrGdwRYXEflpfIO', N'1hndhg')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (65, N'1hdaghn@gmail.com', 1, N'$2a$10$Yibyym7/Cnuw10jX5eF0T.7UjNdwecdvjLliyBbCnr9YWSPDcsyM6', N'1aahndhg')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (66, N'1hdaghnf@gmail.com', 1, N'$2a$10$MxAsxC9Ie2jzCDZuWDM.nuMzeH56iw9/zNf2cKwvw.jm1hRALpIC6', N'1aafhndhg')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (67, N'1hdaghngf@gmail.com', 1, N'$2a$10$60G2hdxLeJicx8HbxJd7Few6Bf.eBkmCrpHZL8d5/H9R2uFKwJSCW', N'1aafhngdhg')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (68, N'1hdaghhngf@gmail.com', 1, N'$2a$10$RHaTK4XeQvGgKDCESC6ly.juCic1XBFyNNN3Pds6kFeoPigZSjavS', N'1aafhhngdhg')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (69, N'1hdaghhngf@gmail.com', 1, N'$2a$10$wT66BX7ARp66uAlUPyIMwuoM7p8m/DrhCFpnLX2I6C6ssUrDo1Ezi', N'1aafhhngdhg')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (70, N'1hdaghhngf@gmail.com', 1, N'$2a$10$3GG/NwqUA4zdICsuwP4m7.OQwysoZ9K67Yll6oYsF5donGq9bcKzy', N'1aafhhngdhg')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (71, N'1hdaghhngf@gmail.com', 1, N'$2a$10$N9GuzxpF9i5Us/PK6NBfv.RL.kJ0dhZb0XscfG3iu1Pg6H15T17.a', N'1aafhhngdhg')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (72, N'1hdaghhngf@gmail.com', 1, N'$2a$10$XMirZJkPoFrHxLuMpAAhE.x7xYbGoJfzUMRVxoW1XZxLzPSnoQbx6', N'1aafhhngdhg')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (82, N'1hdaghhngf@gmail.com', 1, N'$2a$10$MHP8YZMOeitB.SzJL3f.KeYYYU79RJ6E/i2FTLHedXOqFdx2sl/66', N'1aafhhngdhg')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (83, N'1hdaghhngf@gmail.com', 1, N'$2a$10$IxKI3KxrjfqxjcpFDeBvxurioz.9T.1fzn.FhYsKdXZq.Cbh71dtm', N'1aafhhngdhg')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (84, N'1hdaghhngf@gmail.com', 1, N'$2a$10$4EWtoZ6NtU9qR1CqFYBIAu1QCNV4qvgwBs96fKYQxOgK8N4865..C', N'1aafhhngdhg')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (85, N'1hdaghhngf@gmail.com', 1, N'$2a$10$szSEYNDIXvgKlxpEsMRr0OjvWhEELH3cnLtakDSQWWYMkXT775wFK', N'1aafhhngdhg')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (86, N'1hdaghhngf@gmail.com', 1, N'$2a$10$aIFtWy0Pah.O8exNgCx6M.jFkVeLAjIo5M8TzPf.1.eAMm25HXogu', N'1aafhhngdhg')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (87, N'1hdaghhngf@gmail.com', 1, N'$2a$10$EsZNJAsLX1f7FnyTMEm7Bu6ma6B9dMpSr8/CHXd7lJICbpwXiiGn6', N'1aafhhngdhg')
INSERT [dbo].[Account] ([id], [email], [enable], [password], [username]) VALUES (94, N'maikiennn@gmail.com', 1, N'$2a$10$PKWsbFQVACj3SIvfOE1xi.A0Z6Pet0/rEWnCWOz/CQiC8E936mAoO', N'cuongmaikien')
SET IDENTITY_INSERT [dbo].[Account] OFF
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (22, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (23, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (24, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (25, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (26, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (26, 2)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (27, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (28, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (29, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (30, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (31, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (32, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (33, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (34, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (35, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (36, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (37, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (38, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (39, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (40, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (41, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (43, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (44, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (45, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (46, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (47, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (48, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (49, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (50, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (51, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (52, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (53, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (54, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (55, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (56, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (57, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (58, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (59, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (60, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (61, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (65, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (66, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (67, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (68, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (69, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (70, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (71, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (72, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (82, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (83, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (84, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (85, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (86, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (87, 1)
INSERT [dbo].[Account_Role] ([account_id], [role_id]) VALUES (94, 1)
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
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (24, N'Nguyễn Văn Minh', N'0961516942', 25, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (25, N'Nguyễn Văn Minh', N'09615169421', 27, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (26, N'Nguyễn Văn Minh', N'09615169421', 28, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (27, N'anh', N'0961516941', 29, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (28, N'nguyenvanminhhh', N'1223456789', 30, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (29, N'nguyenvanminhhh', N'1223456789', 31, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (30, N'Nguyễn Văn Anh', N'1234567890', 32, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (31, N'Nguyễn Văn Anh', N'1234567890', 33, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (32, N'Nguyễn Văn Anh', N'1234567890', 34, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (33, N'Nguyễn Văn Anh', N'1234567890', 35, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (34, N'Nguyễn Văn Anh', N'1234567890', 36, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (35, N'Nguyễn Văn Anh', N'1234567890', 37, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (36, N'Nguyễn Văn Anh', N'1234567890', 38, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (37, N'Nguyễn Văn Anh', N'1234567890', 39, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (38, N'', N'1234567890', 40, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (39, N'', N'1234567890', 41, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (41, N'', N'1234567890', 43, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (42, N'', N'1234567890', 44, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (43, N'', N'1234567890', 45, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (44, N'', N'1234567890', 46, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (45, N'', N'1234567890', 47, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (46, N'', N'12347890', 48, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (47, N'', N'123478erfdg90', 49, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (48, N'', N'123478erfdg90', 50, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (49, N'', N'123478erfdg90', 51, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (50, N'', N'123478erfdg90', 52, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (51, N'', N'123478erfdg90', 53, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (52, N'', N'12390', 54, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (53, N'', N'12390', 55, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (54, N'', N'12390', 56, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (55, N'', N'12390', 57, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (56, N'', N'12390', 58, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (57, N'', N'12390', 59, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (58, N'', N'12390', 60, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (59, N'', N'12390', 61, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (63, N'', N'12390', 65, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (64, N'', N'12390', 66, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (65, N'', N'12390', 67, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (66, N'', N'12390', 68, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (67, N'', N'12390', 69, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (68, N'', N'12390', 70, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (69, N'', N'12390', 71, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (70, N'', N'12390', 72, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (80, N'', N'12390', 82, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (81, N'', N'12390', 83, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (82, N'', N'12390', 84, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (83, N'', N'12390', 85, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (84, N'', N'12390', 86, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (85, N'', N'12390', 87, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [account_id], [typeCustomer_id]) VALUES (92, N'cuong mai', N'1234567890', 94, 1)
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
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (19, CAST(250000.00 AS Numeric(19, 2)), 1, NULL, 1)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (20, CAST(250000.00 AS Numeric(19, 2)), 1, NULL, 2)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (21, CAST(250000.00 AS Numeric(19, 2)), 1, NULL, 3)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (22, CAST(250000.00 AS Numeric(19, 2)), 1, 6, 1)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (23, CAST(250000.00 AS Numeric(19, 2)), 1, 6, 2)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (24, CAST(250000.00 AS Numeric(19, 2)), 1, 6, 3)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (25, CAST(250000.00 AS Numeric(19, 2)), 1, 7, 1)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (26, CAST(250000.00 AS Numeric(19, 2)), 1, 7, 2)
INSERT [dbo].[OrderDetail] ([id], [price], [quantity], [order_id], [subProduct_id]) VALUES (27, CAST(250000.00 AS Numeric(19, 2)), 1, 7, 3)
SET IDENTITY_INSERT [dbo].[OrderDetail] OFF
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (1, CAST(0x66420B00 AS Date), N'ship cod', N'trường đại học công nghiệp thành phố hồ chí minh', N'ĐÃ_XÁC_NHẬN', CAST(1600000.00 AS Numeric(19, 2)), 21)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (2, CAST(0x66420B00 AS Date), N'ship cod', N'hà trung thanh hóa', N'ĐANG_CHỜ', CAST(1258000.00 AS Numeric(19, 2)), 22)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (3, CAST(0x66420B00 AS Date), N'ship cod', N'vinh nghệ an', N'ĐANG_CHỜ', CAST(616000.00 AS Numeric(19, 2)), 23)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (4, CAST(0x66420B00 AS Date), N'ship cod', N'trường đại học công nghiệp thành phố hồ chí minh', N'ĐANG_CHỜ', CAST(750000.00 AS Numeric(19, 2)), 21)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (5, CAST(0x66420B00 AS Date), N'ship cod', N'trường đại học công nghiệp thành phố hồ chí minh', N'ĐANG_CHỜ', CAST(750000.00 AS Numeric(19, 2)), 21)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (6, CAST(0x66420B00 AS Date), N'ship cod', N'trường đại học công nghiệp thành phố hồ chí minh', N'ĐANG_CHỜ', CAST(750000.00 AS Numeric(19, 2)), 21)
INSERT [dbo].[Orders] ([id], [order_date], [payment_method], [ship_address], [status], [total], [customer_id]) VALUES (7, CAST(0x66420B00 AS Date), N'ship cod', N'trường đại học công nghiệp thành phố hồ chí minh', N'ĐANG_CHỜ', CAST(750000.00 AS Numeric(19, 2)), 21)
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
