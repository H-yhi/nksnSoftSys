package nksnSoftSys.com.controller.ddd;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nksnSoftSys.com.bean.userInfo.UserBean;
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

		UserDao userDao = new UserDao();
		if(userDao.userUp(pass, name, posiId, handId, autFlg, userId)) {
			List<UserBean> list = userDao.findAll();
			request.setAttribute("list", list);
			request.setAttribute("message", "更新に成功しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/applicationList.jsp");
			dispatcher.forward(request, response);
		}else {
			/*if(pass == "") {
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
			}*/
		}
	}

	/*private void reqFlg(String userId, String pass, String name, List<PosiBean> posiBean, List<HandBean> handBean, HttpServletRequest request, List<AutBean> autBean) {
		request.setAttribute("userId",userId);
		request.setAttribute("pass",pass);
		request.setAttribute("name",name);
		request.setAttribute("posiBean",posiBean);
		request.setAttribute("handBean", handBean);
		request.setAttribute("autBean",autBean);
	}*/

}
