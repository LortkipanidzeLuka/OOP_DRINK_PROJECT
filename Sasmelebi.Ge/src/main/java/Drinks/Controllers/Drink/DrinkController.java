package Drinks.Controllers.Drink;

import Drinks.Model.Containers.Drink;
import Drinks.Model.Containers.DrinkFull;
import Drinks.Model.DataBase.DrinkDao.TheDrinkData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class DrinkController {

    @GetMapping(value = "/Drink")
    public ModelAndView getDrinkPage(HttpServletRequest request) throws SQLException {
        String drinkId = request.getParameter("drink_id");
        ModelAndView mw = new ModelAndView("/Drink/showDrink");
        TheDrinkData dt = new TheDrinkData();
        Drink dr = dt.getDrink(Integer.valueOf(drinkId));
        mw.addObject("drink", dr);
        String ranking = request.getParameter("ranking_sc");
        System.out.println(ranking);
        if(ranking != null) dt.justRated(dr.getDrinkId(), 1, Integer.valueOf(ranking));
        DrinkFull drFull = new DrinkFull(dr.getDrinkId(), 1);
        // request.getSession().getAttribute("masterUser").getCurrentUserId();
        mw.addObject("wrappedDrinkInfo", drFull);
        return mw;
    }

    @PostMapping(value = "/Drink")
    public ModelAndView postDrinkPage(HttpServletRequest request) throws SQLException {
        return null;
    }
}