package nksnSoftSys.com.controller.eee;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nksnSoftSys.com.bean.userInfo.UserBean;
import nksnSoftSys.com.dao.kjnGra.KjnGraDao;
import nksnSoftSys.com.dao.user.UserDao;

/**
 * Servlet implementation class EEEupKjnGraController
 */
@WebServlet("/EEEupKjnGraController")
public class EEEupKjnGraController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EEEupKjnGraController() {
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
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		int atBat = Integer.parseInt(request.getParameter("atBat"));
		int batCon = Integer.parseInt(request.getParameter("batCon"));
		int hit = Integer.parseInt(request.getParameter("hit"));
		int secHit = Integer.parseInt(request.getParameter("secHit"));
		int thrHit = Integer.parseInt(request.getParameter("thrHit"));
		int homeRun = Integer.parseInt(request.getParameter("homeRun"));
		int rbi = Integer.parseInt(request.getParameter("rbi"));
		int stBase = Integer.parseInt(request.getParameter("stBase"));
		int foBall = Integer.parseInt(request.getParameter("foBall"));
		int deBall = Integer.parseInt(request.getParameter("deBall"));
		int sacRoll = Integer.parseInt(request.getParameter("sacRoll"));
		int sacFly = Integer.parseInt(request.getParameter("sacFly"));

		if(atBat < 0 || batCon < 0 || hit < 0
				|| secHit < 0 || thrHit < 0 || homeRun < 0
				|| rbi < 0 || stBase < 0 || foBall < 0
				|| deBall < 0 || sacRoll < 0 || sacFly < 0) {
			regUser(userId,request);
			reqFlg(atBat,batCon,hit,secHit,thrHit,homeRun,rbi,stBase,foBall,deBall,sacRoll,sacFly,request);
			request.setAttribute("message","負数があります。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/upKjnGra.jsp");
			dispatcher.forward(request, response);
		}

		KjnGraDao kjnGraDao = new KjnGraDao();

		if(kjnGraDao.kjnGraUp(userId, atBat, batCon, hit, secHit, thrHit, homeRun, rbi, stBase, foBall, deBall, sacRoll, sacFly)) {
			request.setAttribute("message","反映しました。");
			UserDao aaaLoginDao = new UserDao();
			List<UserBean> list = aaaLoginDao.findAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/applicationList.jsp");
			dispatcher.forward(request, response);
		}

	}
	private void reqFlg(int atBat, int batCon, int hit,
			int secHit,int thrHit, int homeRun,
			int rbi, int stBase, int foBall,
			int deBall, int sacRoll, int sacFly, HttpServletRequest request) {
		request.setAttribute("atBat",atBat);
		request.setAttribute("batCon",batCon);
		request.setAttribute("hit",hit);
		request.setAttribute("secHit", secHit);
		request.setAttribute("thrHit",thrHit);
		request.setAttribute("homeRun",homeRun);
		request.setAttribute("rbi",rbi);
		request.setAttribute("stBase",stBase);
		request.setAttribute("foBall",foBall);
		request.setAttribute("deBall",deBall);
		request.setAttribute("sacRoll",sacRoll);
		request.setAttribute("sacFly",sacFly);
	}
	private void regUser(String userId, HttpServletRequest request) {
		UserDao userDao = new UserDao();
		UserBean userBean = userDao.userFind(userId);
		request.setAttribute("userBean",userBean);
	}

}
