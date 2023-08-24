<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="menuToggle"></div>
<div class="siderbar">
    <form action="SvMenu" method="POST">
        <ul>
            <li class="logo" style="--bg:#fff;">
                <a href="#">
                    <div class="icon"></div>
                    <div class="text text-ini">
                        <input type="submit" value="Sistema de alumnos" name="btnInicio">
                    </div>
                </a>
            </li>
            <div class="Menulist">
                <li style="--bg: #09f114">
                    <a href="#">
                        <div class="icon">
                            <ion-icon name="people-outline"></ion-icon>
                        </div>
                        <div class="text">
                            <input type="submit" value="Alumnos" name="btnAlumnos">
                        </div>
                    </a>
                </li>
                <li style="--bg: #0fdfd8;">
                    <a href="#">
                        <div class="icon">
                            <ion-icon name="library-outline"></ion-icon>
                        </div>
                        <div class="text">
                            <input type="submit" value="Materias" name="btnMaterias">
                        </div>
                    </a>
                </li>
                <li style="--bg: #fc1243;">
                    <a href="#">
                        <div class="icon">
                            <ion-icon name="file-tray-full-outline"></ion-icon>
                        </div>
                        <div class="text">
                            <input type="submit" value="Detalles materias" name="btnsMaterias">
                        </div>
                    </a>
                </li>
                <li  style="--bg: #a9249d;">
                    <a href="#">
                        <div class="icon">
                            <ion-icon name="document-text-outline"></ion-icon>
                        </div>
                        <div class="text">
                            <input type="submit" value="Calificaciones" name="btnCalificaciones">
                        </div>
                    </a>
                </li>
                  <li  style="--bg: #fba727;">
                    <a href="#">
                        <div class="icon">
                            <ion-icon name="newspaper-outline"></ion-icon>
                        </div>
                        <div class="text">
                            <input type="submit" value=" Detalles calificaciones" name="btnDCalificaciones">
                        </div>
                    </a>
                </li>
                <div class="bottom">
                    <li style="--bg: #fff;">
                        <a href="#">
                            <div class="icon">
                                <div class="imgBx">
                                    <img src="img/perfil.jpeg">
                                </div>
                            </div>
                            <div class="text">
                                <%
                                    String correo = (String) session.getAttribute("correo");
                                    out.print(correo);
                                %>
                            </div>
                        </a>
                    </li>
                    <li style="--bg: #fff;">
                        <a href="#">
                            <div class="icon"><ion-icon name="exit-outline"></ion-icon></div>
                            <div class="text">
                                <input type="submit" value="Salir de cuenta" name="btnCerrarSession">
                            </div>
                        </a>
                    </li>
                </div>
            </div>
        </ul>
    </form>
</div>