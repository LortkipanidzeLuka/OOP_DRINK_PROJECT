<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <script>
        function add(){
            var container = document.getElementById("inputs");
            var new_select = document.createElement("Select");
            var opt = document.createElement('option');
            var break_line = document.createElement("br");
            opt.value ="A";
            opt.text ="A";

            new_select.setAttribute("name","ingredient[]");
            new_select.appendChild(opt);
            container.appendChild(break_line);

            container.appendChild(new_select);

        }
    </script>
    <head>
        <title>Sasmelebi.ge </title>
    </head>

    <body>
     <div style="position: relative; width: ${window.width()} px;">
        <div style="position: absolute; top: 0; right: 0; width: 100px; text-align:right;">
            <a href="/Login">Logout</a>
        </div>
        <div style="position: absolute; top: 0; right: 70px; width: 100px; text-align:right;">
            <a href="/Favourites">Favourites</a>
        </div>
        <div style="position: absolute; top: 0; right: 180px; width: 100px; text-align:right;">
            <a href="/HomePage">Home Page</a>
        </div>
        <form action="/Search" method="get">
            <div id="inputs">
            <label> Drink Name : </label>
            <input type="text" name="drink_name"> <br>
            <label> Ingredients :  </label> <br>
            <Select  name="ingredient[]"> <br>
                <option value="A">A</option>
            </Select>

            </div>
            <input type="submit" , value = "Search"> <br>
          </form>
         <button onclick="add()">Add Ingredient</button>
    </body>
</html>