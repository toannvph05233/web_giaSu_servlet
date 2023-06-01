package com.web.Controller;

import com.web.Model.GiaSu;
import com.web.Model.LopHoc;
import com.web.Service.GiaSuService;
import com.web.Service.LopHocService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/home")
public class HomeServelet extends HttpServlet {
    GiaSuService giaSuService = new GiaSuService();
    LopHocService lopHocService = new LopHocService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<LopHoc> listLH3 = new ArrayList<>();
        List<LopHoc> listLH4 = new ArrayList<>();
        List<LopHoc> lopHocsLv1 = lopHocService.getAlllopHocs();
        for (int i = 0; i < lopHocsLv1.size(); i++) {
            if (i < 3) {
                listLH3.add(lopHocsLv1.get(i));
            } else if (i < 7) {
                listLH4.add(lopHocsLv1.get(i));
            }
        }
        req.setAttribute("listLH3", listLH3);
        req.setAttribute("listLH4", listLH4);

        List<GiaSu> listGS3 = new ArrayList<>();
        List<GiaSu> listGS4 = new ArrayList<>();
        List<GiaSu> giaSuList = giaSuService.getAllGiaSus();
        for (int i = 0; i < giaSuList.size(); i++) {
            giaSuList.get(i).setCountHV(lopHocService.countHocSinhInGS(giaSuList.get(i).getUsername()));
            giaSuList.get(i).setCountLH(giaSuService.countHocSinhByGiaSuUsername(giaSuList.get(i).getUsername()));
            if (i < 3) {
                listGS3.add(giaSuList.get(i));
            } else if (i < 7) {
                listGS4.add(giaSuList.get(i));
            }
        }


        req.setAttribute("listGS3", listGS3);
        req.setAttribute("listGS4", listGS4);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
