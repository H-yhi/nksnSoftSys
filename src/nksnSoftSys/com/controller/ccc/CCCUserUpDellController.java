package nksnSoftSys.com.controller.ccc;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nksnSoftSys.com.bean.aut.AutBean;
import nksnSoftSys.com.bean.hand.HandBean;
import nksnSoftSys.com.bean.kjnGra.KjnGraBean;
import nksnSoftSys.com.bean.posi.PosiBean;
import nksnSoftSys.com.bean.userInfo.UserBean;
import nksnSoftSys.com.dao.aut.AutDao;
import nksnSoftSys.com.dao.hand.HandDao;
import nksnSoftSys.com.dao.kjnGra.KjnGraDao;
import nksnSoftSys.com.dao.posi.PosiDao;
import nksnSoftSys.com.dao.user.UserDao;

/**
 * Servlet implementation class CCCUserUpDellController
 */
@WebServlet("/CCCUserUpDellController")
public class CCCUserUpDellController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CCCUserUpDellController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*		request.setCharacterEncoding("UTF-8");
				ConDao conDao = new ConDao();
				List<ConBean> conBeanList = conDao.conFindAll();
				request.setAttribute("conBeanList",conBeanList);

				UserDao userDao = new UserDao();
				List<UserBean> userBeanList = userDao.findAll();
				request.setAttribute("userBeanList",userBeanList);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/upKjnGra.jsp");
				dispatcher.forward(request, response);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String update = request.getParameter("update");
		String delete = request.getParameter("delete");
		String kjnGraDay = request.getParameter("kjnGraDay");
		String kjnGra = request.getParameter("kjnGra");

		if (delete != null) {
			UserDao userDao = new UserDao();
			KjnGraDao kjnGraDao = new KjnGraDao();
			if(kjnGraDao.kjnGraDell(delete)) {
				userDao.userDell(delete);
				List<UserBean> list = userDao.findAll();
				request.setAttribute("list", list);
				request.setAttribute("message", "削除に成功しました。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/applicationList.jsp");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("message", "削除に失敗しました。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/applicationList.jsp");
				dispatcher.forward(request, response);
			}
		}else if(update != null) {
			UserDao userDao = new UserDao();
			UserBean userBean = userDao.userFind(update);
			request.setAttribute("userBean", userBean);
			PosiDao posiDao = new PosiDao();
			List<PosiBean> posiBean = posiDao.posiFind();
			request.setAttribute("posiBean",posiBean);
			HandDao handDao = new HandDao();
			List<HandBean> handBean = handDao.handFind();
			request.setAttribute("handBean",handBean);
			AutDao autDao = new AutDao();
			List<AutBean> autBean = autDao.findAll();
			request.setAttribute("autBean",autBean);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/upUser.jsp");
			dispatcher.forward(request, response);
		}else if(kjnGraDay != null) {
			UserDao userDao = new UserDao();
			UserBean userBean = userDao.userFind(kjnGraDay);
			request.setAttribute("userBean",userBean);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/upKjnGra.jsp");
			dispatcher.forward(request, response);
		}else {
			KjnGraDao kjnGraDao = new KjnGraDao();
			KjnGraBean kjnGraBean = kjnGraDao.kjnGraFind(kjnGra);
			request.setAttribute("kjnGraBean",kjnGraBean);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/upKjnGraTotl.jsp");
			dispatcher.forward(request, response);
		}
	}

}
