package nksnSoftSys.com.controller.fff;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nksnSoftSys.com.bean.kjnGra.KjnGraBean;
import nksnSoftSys.com.bean.userInfo.UserBean;
import nksnSoftSys.com.dao.kjnGra.KjnGraDao;
import nksnSoftSys.com.dao.user.UserDao;
import nksnSoftSys.com.form.kjnGra.KjnGraErrorCheck;

/**
 * Servlet implementation class FFFupKjnGraController
 */
@WebServlet("/FFFupKjnGraController")
public class FFFupKjnGraController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FFFupKjnGraController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		try {
			int game = Integer.parseInt(request.getParameter("game"));
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
			KjnGraErrorCheck kjnGraError = new KjnGraErrorCheck();
			if (kjnGraError.minCheck(atBat, batCon, hit, secHit, thrHit, homeRun, rbi, stBase, foBall, deBall, sacRoll,
					sacFly)) {
				regUser(userId, request);
				reqFlg(game, atBat, batCon, hit,
						secHit, thrHit, homeRun,
						rbi, stBase, foBall,
						deBall, sacRoll, sacFly, request);
				request.setAttribute("message", "負数があります。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/upKjnGraTotl.jsp");
				dispatcher.forward(request, response);
			} else if (kjnGraError.batConCheck(atBat, batCon)) {
				regUser(userId, request);
				reqFlg(game, atBat, batCon, hit,
						secHit, thrHit, homeRun,
						rbi, stBase, foBall,
						deBall, sacRoll, sacFly, request);
				request.setAttribute("message", "打席より打数が多いです");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/upKjnGraTotl.jsp");
				dispatcher.forward(request, response);
			} else if (kjnGraError.hitCheck(atBat, hit, secHit, thrHit, homeRun)) {
				regUser(userId, request);
				reqFlg(game, atBat, batCon, hit,
						secHit, thrHit, homeRun,
						rbi, stBase, foBall,
						deBall, sacRoll, sacFly, request);
				request.setAttribute("message", "打席よりヒットの数が多いです");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/upKjnGraTotl.jsp");
				dispatcher.forward(request, response);
			} else if (kjnGraError.rbiCheck(homeRun, rbi)) {
				regUser(userId, request);
				reqFlg(game, atBat, batCon, hit,
						secHit, thrHit, homeRun,
						rbi, stBase, foBall,
						deBall, sacRoll, sacFly, request);
				request.setAttribute("message", "打点よりホームランが多いです");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/upKjnGraTotl.jsp");
				dispatcher.forward(request, response);
			} else if (kjnGraError.atBatCheck(atBat, batCon, foBall, deBall, sacRoll, sacFly)) {
				regUser(userId, request);
				reqFlg(game, atBat, batCon, hit,
						secHit, thrHit, homeRun,
						rbi, stBase, foBall,
						deBall, sacRoll, sacFly, request);
				request.setAttribute("message", "打席または打数の結果を見直してください");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/upKjnGraTotl.jsp");
				dispatcher.forward(request, response);
			} else if(kjnGraError.gameCheck(game, atBat, batCon, hit, secHit, thrHit, homeRun, rbi, stBase, foBall, deBall, sacRoll, sacFly)) {
				regUser(userId, request);
				reqFlg(game, atBat, batCon, hit,
						secHit, thrHit, homeRun,
						rbi, stBase, foBall,
						deBall, sacRoll, sacFly, request);
				request.setAttribute("message", "試合数を入力してください");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/upKjnGraTotl.jsp");
				dispatcher.forward(request, response);
			} else {
				KjnGraDao kjnGraDao = new KjnGraDao();
				if (kjnGraDao.kjnGraUp(userId, game, atBat,
						batCon, hit, secHit,
						thrHit, homeRun, rbi,
						stBase, foBall, deBall,
						sacRoll, sacFly)) {
					request.setAttribute("message", "反映しました。");
					UserDao aaaLoginDao = new UserDao();
					List<UserBean> list = aaaLoginDao.findAll();
					request.setAttribute("list", list);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/applicationList.jsp");
					dispatcher.forward(request, response);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			String game = request.getParameter("game");
			String atBat = request.getParameter("atBat");
			String batCon = request.getParameter("batCon");
			String hit = request.getParameter("hit");
			String secHit = request.getParameter("secHit");
			String thrHit = request.getParameter("thrHit");
			String homeRun = request.getParameter("homeRun");
			String rbi = request.getParameter("rbi");
			String stBase = request.getParameter("stBase");
			String foBall = request.getParameter("foBall");
			String deBall = request.getParameter("deBall");
			String sacRoll = request.getParameter("sacRoll");
			String sacFly = request.getParameter("sacFly");
			regUser(userId, request);
			sReqFlg(game, atBat, batCon, hit, secHit, thrHit, homeRun, rbi, stBase, foBall, deBall, sacRoll, sacFly,
					request);
			request.setAttribute("message", "'数値'を入力しください！！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/upKjnGraTotl.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void reqFlg(int game, int atBat, int batCon, int hit,
			int secHit, int thrHit, int homeRun,
			int rbi, int stBase, int foBall,
			int deBall, int sacRoll, int sacFly, HttpServletRequest request) {
		request.setAttribute("game", game);
		request.setAttribute("atBat", atBat);
		request.setAttribute("batCon", batCon);
		request.setAttribute("hit", hit);
		request.setAttribute("secHit", secHit);
		request.setAttribute("thrHit", thrHit);
		request.setAttribute("homeRun", homeRun);
		request.setAttribute("rbi", rbi);
		request.setAttribute("stBase", stBase);
		request.setAttribute("foBall", foBall);
		request.setAttribute("deBall", deBall);
		request.setAttribute("sacRoll", sacRoll);
		request.setAttribute("sacFly", sacFly);
	}

	private void regUser(String userId, HttpServletRequest request) {
		KjnGraDao kjnGraDao = new KjnGraDao();
		KjnGraBean kjnGraBean = kjnGraDao.kjnGraFind(userId);
		request.setAttribute("kjnGraBean",kjnGraBean);
	}

	private void sReqFlg(String game, String atBat, String batCon, String hit,
			String secHit, String thrHit, String homeRun,
			String rbi, String stBase, String foBall,
			String deBall, String sacRoll, String sacFly, HttpServletRequest request) {
		request.setAttribute("game", game);
		request.setAttribute("atBat", atBat);
		request.setAttribute("batCon", batCon);
		request.setAttribute("hit", hit);
		request.setAttribute("secHit", secHit);
		request.setAttribute("thrHit", thrHit);
		request.setAttribute("homeRun", homeRun);
		request.setAttribute("rbi", rbi);
		request.setAttribute("stBase", stBase);
		request.setAttribute("foBall", foBall);
		request.setAttribute("deBall", deBall);
		request.setAttribute("sacRoll", sacRoll);
		request.setAttribute("sacFly", sacFly);
	}

}
