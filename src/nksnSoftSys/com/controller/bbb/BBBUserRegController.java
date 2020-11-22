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

import nksnSoftSys.com.bean.posi.PosiBean;
import nksnSoftSys.com.bean.userInfo.UserBean;
import nksnSoftSys.com.dao.kjnGra.kjnGraDao;
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
		String posiId= request.getParameter("posiId");

		String regex_num = "^[0-9]+$" ; // 数値のみ
		Pattern p1 = Pattern.compile(regex_num);
		Matcher m1 = p1.matcher(userId);
		Matcher m2 = p1.matcher(pass);
		boolean result11 = m1.matches();
		boolean result12 = m2.matches();

		UserDao aaaLoginDao = new UserDao();
		PosiDao posiDao = new PosiDao();
		List<PosiBean> posiBean = posiDao.posiFind();
		if(aaaLoginDao.regUser(userId, pass, name, posiId)) {
			kjnGraDao kjnGraDao = new  kjnGraDao();
			kjnGraDao.regKjnGra(userId);
			List<UserBean> list = aaaLoginDao.findAll();
			request.setAttribute("list", list);
			request.setAttribute("message", "登録に成功しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/applicationList.jsp");
			dispatcher.forward(request, response);
		}else {
			if(userId == "") {
				request.setAttribute("userId",userId);
				request.setAttribute("pass",pass);
				request.setAttribute("name",name);
				request.setAttribute("posiBean",posiBean);
				request.setAttribute("message", "ユーザーIDが空白です");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
				dispatcher.forward(request, response);
			}else if(userId.length() != 4){
				request.setAttribute("userId",userId);
				request.setAttribute("pass",pass);
				request.setAttribute("name",name);
				request.setAttribute("posiBean",posiBean);
				request.setAttribute("message", "ユーザーIDは４文字固定です");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
				dispatcher.forward(request, response);
			}else if(result11 == false) {
				request.setAttribute("userId",userId);
				request.setAttribute("pass",pass);
				request.setAttribute("name",name);
				request.setAttribute("posiBean",posiBean);
				request.setAttribute("message", "ユーザーIDは数値のみです");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
				dispatcher.forward(request, response);
			}
			if(pass == "") {
				request.setAttribute("userId",userId);
				request.setAttribute("pass",pass);
				request.setAttribute("name",name);
				request.setAttribute("message", "パスワードが空白です");
				request.setAttribute("posiBean",posiBean);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
				dispatcher.forward(request, response);
			}else if(pass.length() != 4) {
				request.setAttribute("userId",userId);
				request.setAttribute("pass",pass);
				request.setAttribute("name",name);
				request.setAttribute("posiBean",posiBean);
				request.setAttribute("message", "パスワードは４文字固定です");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
				dispatcher.forward(request, response);
			}else if(result12 == false) {
				request.setAttribute("userId",userId);
				request.setAttribute("pass",pass);
				request.setAttribute("name",name);
				request.setAttribute("posiBean",posiBean);
				request.setAttribute("message", "パスワードは数値のみです");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
				dispatcher.forward(request, response);
			}
			if(name == "") {
				request.setAttribute("userId",userId);
				request.setAttribute("pass",pass);
				request.setAttribute("name",name);
				request.setAttribute("posiBean",posiBean);
				request.setAttribute("message", "名前が空白です");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
				dispatcher.forward(request, response);
			}
			request.setAttribute("userId",userId);
			request.setAttribute("pass",pass);
			request.setAttribute("name",name);
			request.setAttribute("posiBean",posiBean);
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
		dispatcher.forward(request, response);
	}

}
