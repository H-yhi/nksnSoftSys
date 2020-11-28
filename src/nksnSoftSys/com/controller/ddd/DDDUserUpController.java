package nksnSoftSys.com.controller.ddd;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nksnSoftSys.com.bean.aut.AutBean;
import nksnSoftSys.com.bean.hand.HandBean;
import nksnSoftSys.com.bean.posi.PosiBean;
import nksnSoftSys.com.bean.userInfo.UserBean;
import nksnSoftSys.com.dao.aut.AutDao;
import nksnSoftSys.com.dao.hand.HandDao;
import nksnSoftSys.com.dao.posi.PosiDao;
import nksnSoftSys.com.dao.user.UserDao;

/**
 * Servlet implementation class DDDUserUpController
 */
@WebServlet("/DDDUserUpController")
public class DDDUserUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DDDUserUpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String posiId = request.getParameter("posiId");
		String handId = request.getParameter("handId");
		String autFlg = request.getParameter("autFlg");
		String userId = request.getParameter("userId");

		String url = "/WEB-INF/views/applicationList.jsp"; // update成功後
		String url2 = "/WEB-INF/views/upUser.jsp"; // update失敗後

		String regex_num = "^[0-9]+$" ; // 数値のみ
		Pattern p1 = Pattern.compile(regex_num);
		Matcher m2 = p1.matcher(pass);
		boolean result12 = m2.matches();

		UserDao userDao = new UserDao();
		if(userDao.userUp(pass, name, posiId, handId, autFlg, userId)) {
			List<UserBean> list = userDao.findAll();
			request.setAttribute("list", list);
			request.setAttribute("message", "更新に成功しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}else {
			if(pass == "") {
				list(request);
				reqFlg(userId,pass,name,posiId,handId,request,autFlg);
				request.setAttribute("message", "パスワードが空白です");
				RequestDispatcher dispatcher = request.getRequestDispatcher(url2);
				dispatcher.forward(request, response);
			}else if(pass.length() != 4) {
				list(request);
				reqFlg(userId,pass,name,posiId,handId,request,autFlg);
				request.setAttribute("message", "パスワードは４文字固定です");
				RequestDispatcher dispatcher = request.getRequestDispatcher(url2);
				dispatcher.forward(request, response);
			}else if(result12 == false) {
				list(request);
				reqFlg(userId,pass,name,posiId,handId,request,autFlg);
				request.setAttribute("message", "パスワードは数値のみです");
				RequestDispatcher dispatcher = request.getRequestDispatcher(url2);
				dispatcher.forward(request, response);
			}
			if(name == "") {
				list(request);
				reqFlg(userId,pass,name,posiId,handId,request,autFlg);
				request.setAttribute("message", "名前が空白です");
				RequestDispatcher dispatcher = request.getRequestDispatcher(url2);
				dispatcher.forward(request, response);
			}
		}
	}

	private void reqFlg(String userId, String pass, String name, String posiId, String handId, HttpServletRequest request, String autFlg) {
		request.setAttribute("userId",userId);
		request.setAttribute("pass",pass);
		request.setAttribute("name",name);
		request.setAttribute("posiId",posiId);
		request.setAttribute("handId", handId);
		request.setAttribute("autFlg",autFlg);
	}

	private void list(HttpServletRequest request) {
		PosiDao posiDao = new PosiDao();
		List<PosiBean> posiBean = posiDao.posiFind();
		request.setAttribute("posiBean",posiBean);
		HandDao handDao = new HandDao();
		List<HandBean> handBean = handDao.handFind();
		request.setAttribute("handBean",handBean);
		AutDao autDao = new AutDao();
		List<AutBean> autBean = autDao.findAll();
		request.setAttribute("autBean",autBean);
	}

}
