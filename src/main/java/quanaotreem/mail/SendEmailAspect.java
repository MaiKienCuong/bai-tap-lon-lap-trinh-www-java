package quanaotreem.mail;

import java.text.DecimalFormat;
import java.util.Iterator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import quanaotreem.entity.Customer;
import quanaotreem.entity.OrderDetail;
import quanaotreem.entity.Orderr;
import quanaotreem.entity.SubProduct;
import quanaotreem.enumvalue.EnumPaymentMethod;

@Aspect
@Component
public class SendEmailAspect {

	@Autowired
	private MailService mailService;

	@Value("${spring.mail.username}")
	private String mailFrom;

	@Value("${shipping}")
	private double ship;

	private DecimalFormat df = new DecimalFormat("###,###,###.0");

	@AfterReturning(pointcut = "execution(* quanaotreem.controller.api.AuthApi.signup(..))", returning = "responseEntity")
	public void afterSignup(JoinPoint joinPoint, ResponseEntity<?> responseEntity) {
		Customer customer = (Customer) joinPoint.getArgs()[0];
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			new Thread(() -> {
				Mail mail = new Mail();
				mail.setMailFrom(mailFrom);
				mail.setMailTo(customer.getEmail());
				mail.setMailSubject("Đăng ký tài khoản thành công");
				mail.setMailContent(
						"Xin chào " + customer.getName() + "! Bạn đã đăng ký tài khoản thành công với tên đăng nhập "
								+ customer.getAccount().getUsername()
								+ "\n\n Đăng nhập tại <a href='http://localhost:3000/login'>đây</a>");
				mailService.sendEmail(mail);
			}).start();
		}
	}

	@AfterReturning(pointcut = "execution(* quanaotreem.controller.api.CustomerApi.addCustomer(..))", returning = "responseEntity")
	public void afterAddCustomer(JoinPoint joinPoint, ResponseEntity<?> responseEntity) {
		Customer customer = (Customer) joinPoint.getArgs()[0];
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			new Thread(() -> {
				Mail mail = new Mail();
				mail.setMailFrom(mailFrom);
				mail.setMailTo(customer.getEmail());
				mail.setMailSubject("Đăng ký tài khoản thành công");
				mail.setMailContent(
						"Xin chào " + customer.getName() + "! Bạn đã đăng ký tài khoản thành công với tên đăng nhập "
								+ customer.getAccount().getUsername()
								+ "\n\n Đăng nhập tại <a href='http://localhost:3000/login'>đây</a>");
				mailService.sendEmail(mail);
			}).start();
		}
	}

	@AfterReturning(pointcut = "execution(* quanaotreem.controller.api.OrderApi.addOrder(..))", returning = "responseEntity")
	public void afterAddOrder(JoinPoint joinPoint, ResponseEntity<?> responseEntity) {
		Orderr order = (Orderr) joinPoint.getArgs()[0];
		Customer customer = order.getCustomer();
		if (order.getPaymentMethod().equals(EnumPaymentMethod.STORE))
			ship = 0;
		StringBuilder content = new StringBuilder("<div class=\"container\">\r\n"
				+ "        <div style=\"font-family: 'Arial',Helvetica Neue,Helvetica,sans-serif; line-height: 14pt;padding:20px 0px;font-size:14px;max-width:580px;margin:0 auto\">\r\n"
				+ "            <div style=\"padding:0 10px;margin-bottom:25px\">\r\n" + "                <p>Xin chào "
				+ customer.getName() + "</p>\r\n"
				+ "                <p>Cảm ơn anh chị đã đặt hàng tại <strong>Quần áo trẻ em</strong>!</p>\r\n"
				+ "                <p>Đơn hàng của anh/chị đã được xác nhận thành công ngày " + order.getOrderDate()
				+ ".</p>\r\n" + "            </div>\r\n" + "            <hr />\r\n"
				+ "            <div style=\"padding:0 10px\">\r\n"
				+ "                <table style=\"width: 100%; border-collapse: collapse;margin-top:20px\">\r\n"
				+ "                    <thead>\r\n" + "                        <tr>\r\n"
				+ "                            <th style=\"text-align: left;width:50%;font-size:medium;padding:5px 0\"><strong>Thông tin khách hàng</strong></th>\r\n"
				+ "                            <th style=\"text-align: left;width:50%;font-size:medium;padding:5px 0\"><strong>Địa chỉ nhận hàng</strong></th>\r\n"
				+ "                        </tr>\r\n" + "                    </thead>\r\n"
				+ "                    <tbody>\r\n" + "                        <tr>\r\n"
				+ "                            <td style=\"padding-right:15px\">\r\n"
				+ "                                <table style=\"width: 100%;\">\r\n"
				+ "                                    <tbody>\r\n" + "                                        <tr>\r\n"
				+ "                                            <td>" + customer.getName() + "</td>\r\n"
				+ "                                        </tr>\r\n"
				+ "                                        <tr>\r\n"
				+ "                                            <td>" + customer.getPhone() + "</td>\r\n"
				+ "                                        </tr>\r\n"
				+ "                                        <tr>\r\n"
				+ "                                            <td style=\"word-break: break-word;word-wrap:  break-word;\"><a href=\"mailto:"
				+ customer.getEmail() + "\">" + customer.getEmail() + "</a></td>\r\n"
				+ "                                        </tr>\r\n"
				+ "                                        <tr>\r\n"
				+ "                                            <td style=\"word-break: break-word;word-wrap:  break-word;\">"
				+ customer.getAddress() + "</td>\r\n" + "                                        </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                </table>\r\n"
				+ "                            </td>\r\n" + "                            <td>\r\n"
				+ "                                <table style=\"width: 100%;\">\r\n"
				+ "                                    <tbody>\r\n" + "                                        <tr>\r\n"
				+ "                                            <td>" + customer.getName() + "</td>\r\n"
				+ "                                        </tr>\r\n"
				+ "                                        <tr>\r\n"
				+ "                                            <td>" + customer.getPhone() + "</td>\r\n"
				+ "                                        </tr>\r\n"
				+ "                                        <tr>\r\n"
				+ "                                            <td>&nbsp;</td>\r\n"
				+ "                                        </tr>\r\n"
				+ "                                        <tr>\r\n"
				+ "                                            <td style=\"word-break: break-word;word-wrap:  break-word;\">"
				+ customer.getAddress() + "</td>\r\n" + "                                        </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                </table>\r\n"
				+ "                            </td>\r\n" + "                        </tr>\r\n"
				+ "                    </tbody>\r\n" + "                </table>\r\n"
				+ "                <table style=\"width: 100%; border-collapse: collapse;margin-top:20px\">\r\n"
				+ "                    <thead>\r\n" + "                        <tr>\r\n"
				+ "                            <th style=\"text-align: left;width:50%;font-size:medium;padding:5px 0\">Hình thức thanh toán:</th>\r\n"
				+ "                        </tr>\r\n" + "                    </thead>\r\n"
				+ "                    <tbody>\r\n" + "                        <tr>\r\n"
				+ "                            <td style=\"padding-right:15px\">" + order.getPaymentMethod()
				+ "</td>\r\n" + "                        </tr>\r\n" + "                    </tbody>\r\n"
				+ "                </table>\r\n" + "            </div>\r\n"
				+ "            <div style=\"margin-top:20px;padding:0 10px\">\r\n"
				+ "                <div style=\"padding-top: 10px;font-size:medium\"><strong>Thông tin đơn hàng:</strong></div>\r\n"
				+ "                <table style=\"width:100%;margin:10px 0\">\r\n" + "                    <tr>\r\n"
				+ "                        <td style=\"width:50%;padding-right:15px\">Mã đơn hàng: " + order.getId()
				+ "</td>\r\n" + "                        <td style=\"width:50%;\">Ngày đặt hàng: "
				+ order.getOrderDate() + "</td>\r\n" + "                    </tr>\r\n" + "                </table>\r\n"
				+ "                <ul style=\"padding-left:0;list-style-type:none;margin-bottom:0;\">");
		for (Iterator<OrderDetail> iterator = order.getOrderDetails().iterator(); iterator.hasNext();) {
			OrderDetail detail = iterator.next();
			SubProduct subProduct = detail.getSubProduct();
			String s = "<li style=''>\r\n"
					+ "                        <table style='width:100%;border-bottom:1px solid #e4e9eb;'>\r\n"
					+ "                            <tr>\r\n"
					+ "                                <td style='width:100%;padding:25px 10px 0px 0' colspan='2'>\r\n"
					+ "            <div\r\n"
					+ "                                        style='float:left;width:80px;height:80px;border:1px solid #ebeff2;overflow: hidden;'>\r\n"
					+ "                                        <img style='max-width:100%;max-height:100%' src='"
					+ subProduct.getProduct().getImagesUrl().get(0).getUrl() + "'>\r\n"
					+ "                                    </div>\r\n"
					+ "                                    <div style='margin-left:100px;'>\r\n"
					+ "                                        <a href='' style='color:#357ebd;text-decoration:none'>"
					+ subProduct.getName() + "</a>\r\n"
					+ "                                        <p style='color:#678299;margin-bottom:0;margin-top:8px'> </p>\r\n"
					+ "                                        <p style='color:#678299;margin-bottom:0;margin-top:8px'>Mã sản phẩm: "
					+ subProduct.getId() + "</p>\r\n" + "                                    </div>\r\n"
					+ "                                </td>\r\n" + "                            </tr>\r\n"
					+ "                            <tr>\r\n"
					+ "                                <td style='width:70%;padding:5px 0px 25px'>\r\n"
					+ "                                    <div style='margin-left:100px;'>"
					+ subProduct.getProduct().getPrice() + " <span style='margin-left:20px'>x " + detail.getQuantity()
					+ "</span>\r\n" + "                                    </div>\r\n"
					+ "                                </td>\r\n"
					+ "                                <td style='text-align:right;width:30%;padding:5px 0px 25px;'>"
					+ subProduct.getProduct().getPrice() * (detail.getQuantity()) + "</td>\r\n"
					+ "                            </tr>\r\n" + "                        </table>\r\n"
					+ "                    </li>";
			content.append(s);
		}
		content.append("</ul>\r\n"
				+ "                <table style=\"width:100%;border-collapse:collapse;margin-bottom:50px;margin-top:10px\">\r\n"
				+ "                    <tr>\r\n" + "                        <td style=\"width:20%\"></td>\r\n"
				+ "                        <td style=\"width:80%\">\r\n"
				+ "                            <table style=\"width:100%;float:right\">\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td style=\"padding-bottom:10px;\">Phí vận chuyển:</td>\r\n"
				+ "                                    <td style=\"font-weight:bold;text-align:right;padding-bottom:10px;\">"
				+ ship + " VND</td>\r\n" + "                                </tr>\r\n"
				+ "                                <tr style=\"border-top:1px solid #e5e9ec\">\r\n"
				+ "                                    <td style=\"padding-top:10px;\">Thành tiền:</td>\r\n"
				+ "                                    <td style=\"font-weight:bold;text-align:right;font-size:16px;padding-top:10px;\">"
				+ df.format(order.sumTotal() + ship) + " VND</td>\r\n" + "                                </tr>\r\n"
				+ "                            </table>\r\n" + "                        </td>\r\n"
				+ "                    </tr>\r\n" + "                </table>\r\n" + "            </div>\r\n"
				+ "            <div style=\"clear:both\"></div>\r\n" + "            <div style=\"padding:0 10px\">\r\n"
				+ "            </div>\r\n" + "            <div style=\"clear:both\"></div>\r\n"
				+ "            <div style=\"padding:0 10px\">\r\n" + "                <p style=\"height:50px\">\r\n"
				+ "                    <span style=\"float:left;margin-top:14px;margin-right:10px\">Để kiểm tra lại đơn hàng, Anh/chị vui lòng:<p></p></span><span style=\"margin-top: 25px;float:left\">\r\n"
				+ "                        <span style=\"padding: 14px 35px; background:#357ebd\">\r\n"
				+ "                            <a href=\"http://localhost:3000/login\" style=\"font-size: 16px; text-decoration: none; color: #fff;\">\r\n"
				+ "                                Đăng nhập vào tài khoản\r\n" + "                            </a>\r\n"
				+ "                        </span>\r\n" + "                    </span>\r\n" + "                </p>\r\n"
				+ "                <div style=\"clear:both\"></div>\r\n"
				+ "                <p style=\"margin:30px 0\">Nếu Anh/chị có bất kỳ câu hỏi nào, xin liên hệ với chúng tôi tại: <a href=\"mailto:"
				+ mailFrom + "\" style=\"color:#357ebd\">" + mailFrom + "</a></p>\r\n"
				+ "                <p style=\"text-align: right;\"><i>Trân trọng, </i></p>\r\n"
				+ "                <p style=\"text-align: right;\"><strong>Quản lý của hàng Quần áo trẻ em</strong></p>\r\n"
				+ "            </div>\r\n" + "        </div>\r\n" + "    </div>");
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			new Thread(() -> {
				Mail mail = new Mail();
				mail.setMailFrom(mailFrom);
				mail.setMailTo(customer.getEmail());
				mail.setMailSubject("Đặt hàng thành công");
				mail.setMailContent(content.toString());
				mailService.sendEmail(mail);
			}).start();
		}
	}

}
