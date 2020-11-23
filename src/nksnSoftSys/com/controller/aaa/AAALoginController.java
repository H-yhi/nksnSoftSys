package nksnSoftSys.com.controller.aaa;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nksnSoftSys.com.bean.userInfo.UserBean;
import nksnSoftSys.com.dao.user.UserDao;

/**
 * Servlet implementation class AAALoginController
 */
@WebServlet("/AAALoginController")
public class AAALoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AAALoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");

		UserDao aaaLoginDao = new UserDao();

		if(aaaLoginDao.checkUser(userId,pass) != null) {
			UserBean userBean = aaaLoginDao.checkUser(userId,pass);
			HttpSession session = request.getSession(true);
			List<UserBean> list = aaaLoginDao.findAll();
			request.setAttribute("list", list);
			session.setAttribute("userBean",userBean);
			request.setAttribute("message", "ログインに成功しました!!!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/applicationList.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("userId",userId);
			request.setAttribute("pass",pass);
			request.setAttribute("message", "IDまたはパスワードが違います");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.invalidate();
		request.setAttribute("message", "ログアウトしました。");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);
	}
}
