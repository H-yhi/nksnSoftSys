package nksnSoftSys.com.controller.bbb;

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
import nksnSoftSys.com.dao.kjnGra.KjnGraDao;
import nksnSoftSys.com.dao.posi.PosiDao;
import nksnSoftSys.com.dao.user.UserDao;

/**
 * Servlet implementation class BBBUserListController
 */
@WebServlet("/BBBUserRegController")
public class BBBUserRegController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BBBUserRegController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");

    	String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String posiId = request.getParameter("posiId");
		// 20201122 start
		String handId = request.getParameter("handId");
		String autFlg = request.getParameter("autFlg");
		// 20201122 end

		String regex_num = "^[0-9]+$" ; // 数値のみ
		Pattern p1 = Pattern.compile(regex_num);
		Matcher m1 = p1.matcher(userId);
		Matcher m2 = p1.matcher(pass);
		boolean result11 = m1.matches();
		boolean result12 = m2.matches();

		UserDao aaaLoginDao = new UserDao();
		PosiDao posiDao = new PosiDao();
		List<PosiBean> posiBean = posiDao.posiFind();
		// 20201122 start
		HandDao handDao = new HandDao();
		List<HandBean> handBean = handDao.handFind();
		AutDao autDao = new AutDao();
		List<AutBean> autBean = autDao.findAll();
		//if(aaaLoginDao.regUser(userId, pass, name, posiId)) {
		if(aaaLoginDao.regUser(userId, pass, name, posiId, handId, autFlg)) {
		// 20201122 end
			nksnSoftSys.com.dao.kjnGra.KjnGraDao kjnGraDao = new KjnGraDao();
			kjnGraDao.regKjnGra(userId);
			List<UserBean> list = aaaLoginDao.findAll();
			request.setAttribute("list", list);
			request.setAttribute("message", "登録に成功しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/applicationList.jsp");
			dispatcher.forward(request, response);
		}else {
			if(userId == "") {
				reqFlg(userId,pass,name,posiBean,handBean,request,autBean);
				request.setAttribute("message", "ユーザーIDが空白です");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
				dispatcher.forward(request, response);
			}else if(userId.length() != 4){
				reqFlg(userId,pass,name,posiBean,handBean,request,autBean);
				request.setAttribute("message", "ユーザーIDは４文字固定です");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
				dispatcher.forward(request, response);
			}else if(result11 == false) {
				reqFlg(userId,pass,name,posiBean,handBean,request,autBean);
				request.setAttribute("message", "ユーザーIDは数値のみです");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
				dispatcher.forward(request, response);
			}
			if(pass == "") {
				reqFlg(userId,pass,name,posiBean,handBean,request,autBean);
				request.setAttribute("message", "パスワードが空白です");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
				dispatcher.forward(request, response);
			}else if(pass.length() != 4) {
				reqFlg(userId,pass,name,posiBean,handBean,request,autBean);
				request.setAttribute("message", "パスワードは４文字固定です");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
				dispatcher.forward(request, response);
			}else if(result12 == false) {
				reqFlg(userId,pass,name,posiBean,handBean,request,autBean);
				request.setAttribute("message", "パスワードは数値のみです");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
				dispatcher.forward(request, response);
			}
			if(name == "") {
				reqFlg(userId,pass,name,posiBean,handBean,request,autBean);
				request.setAttribute("message", "名前が空白です");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
				dispatcher.forward(request, response);
			}
			reqFlg(userId,pass,name,posiBean,handBean,request,autBean);
			request.setAttribute("message", "システムエラーです");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
			dispatcher.forward(request, response);
		}


    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		PosiDao posiDao = new PosiDao();
		List<PosiBean> posiBean = posiDao.posiFind();
		request.setAttribute("posiBean",posiBean);
		// 20201122 start
		HandDao handDao = new HandDao();
		List<HandBean> handBean = handDao.handFind();
		request.setAttribute("handBean",handBean);
		AutDao autDao = new AutDao();
		List<AutBean> autBean = autDao.findAll();
		request.setAttribute("autBean",autBean);
		// 20201122 end
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
		dispatcher.forward(request, response);
	}
	// 20201122 start
	private void reqFlg(String userId, String pass, String name, List<PosiBean> posiBean, List<HandBean> handBean, HttpServletRequest request, List<AutBean> autBean) {
		request.setAttribute("userId",userId);
		request.setAttribute("pass",pass);
		request.setAttribute("name",name);
		request.setAttribute("posiBean",posiBean);
		request.setAttribute("handBean", handBean);
		request.setAttribute("autBean",autBean);
	}
	// 20201122 end

}
