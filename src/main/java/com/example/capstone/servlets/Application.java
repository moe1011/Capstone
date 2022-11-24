package com.example.capstone.servlets;

import com.example.capstone.beans.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import com.example.capstone.beans.Store;
import com.example.capstone.beans.StoreBuilder;

import static java.lang.Long.parseLong;

@WebServlet(name = "home", value = "/home")
public class Application extends HttpServlet {

    static Admin admin = new Admin();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/home.jsp").include(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/home.jsp").include(request, response);
    }



    @WebServlet(name = "stores", value = "/stores")
    public static class Stores extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            admin.setStores(new ArrayList<>()); // Temporary
//            admin.addStore(new Store("Walmart", "123 Street"));
//            admin.addStore(new Store("Costco", "456 Avenue"));
            request.setAttribute("stores", admin.getStores());

            String selectedStore = request.getParameter("selectedStore");
//            System.out.println(selectedStore);
            if (selectedStore != null){
                Store chosenStore = admin.getStores().get(Integer.parseInt(selectedStore));
                request.setAttribute("selectedStoreIndex", Integer.parseInt(selectedStore));

                request.setAttribute("selectedStore", chosenStore);
            }

            request.getRequestDispatcher("/stores.jsp").include(request, response);


        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            request.setAttribute("stores", admin.getStores());
            request.getRequestDispatcher("/stores.jsp").include(request, response);
        }

        @WebServlet(name = "addstores", value = "/addstores")
        public static class AddStores extends HttpServlet {

            @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String storeName = request.getParameter("storeName");
                String storeAddress = request.getParameter("storeAddress");

                if(storeName != null && storeAddress != null){
                    admin.addStore(new StoreBuilder().setStoreName(storeName).setStoreAddress(storeAddress).createStore());
                }


                request.getRequestDispatcher("/stores.jsp").include(request, response);
            }

            @Override
            protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                request.getRequestDispatcher("/stores.jsp").include(request, response);
            }

        }
        @WebServlet(name = "editstores", value = "/editstores")
        public static class EditStores extends HttpServlet {
            Store chosenStore = null;
            @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                request.setAttribute("stores", admin.getStores());

                String selectedStore = request.getParameter("selectedStore");
                if (selectedStore != null){
                    chosenStore = admin.getStores().get(Integer.parseInt(selectedStore));
                    request.setAttribute("selectedStoreIndex", Integer.parseInt(selectedStore));

                    request.setAttribute("selectedStore", chosenStore);
                }

                String storeName = request.getParameter("storeName");
                String storeAddress = request.getParameter("storeAddress");
                long storeId = request.getParameter("id") != null ? Long.parseLong(request.getParameter("id")) : -1;

                if(storeName != null && storeAddress != null){
                    Store fetchedStore = admin.getStoreById(storeId);
                    if(fetchedStore != null){
                        fetchedStore.setStoreName(storeName);
                        fetchedStore.setStoreAddress(storeAddress);
                        admin.setStoreById(storeId, fetchedStore);
                    }
                }


                request.getRequestDispatcher("/stores.jsp").include(request, response);
            }

            @Override
            protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                request.getRequestDispatcher("/stores.jsp").include(request, response);
            }

        }
        @WebServlet(name = "removestores", value = "/removestores")
        public static class RemoveStores extends HttpServlet {
            Store chosenStore = null;

            @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                request.setAttribute("stores", admin.getStores());

                String selectedStore = request.getParameter("selectedStore");

                if (selectedStore != null){
                    chosenStore = admin.getStores().get(Integer.parseInt(selectedStore));
                    request.setAttribute("selectedStoreIndex", Integer.parseInt(selectedStore));

                    request.setAttribute("selectedStore", chosenStore);
                }

                if(request.getParameter("removeStore") != null && chosenStore != null){
                        admin.removeStore(chosenStore);
                }


                request.getRequestDispatcher("/stores.jsp").include(request, response);
            }

            @Override
            protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                request.getRequestDispatcher("/stores.jsp").include(request, response);
            }

        }

        // Games Section
        @WebServlet(name = "addgames", value = "/addgames")
        public static class AddGames extends HttpServlet {
            Store chosenStore = null;
            @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                request.setAttribute("stores", admin.getStores());

                String selectedStore = request.getParameter("selectedStore");
                if (selectedStore != null){
                    chosenStore = admin.getStores().get(Integer.parseInt(selectedStore));
                    request.setAttribute("selectedStoreIndex", Integer.parseInt(selectedStore));

                    request.setAttribute("selectedStore", chosenStore);
                }

                String gameName = request.getParameter("gameName");

                if(gameName != null){
                    chosenStore.addGame(gameName);
                }


                request.getRequestDispatcher("/stores.jsp").include(request, response);
            }

            @Override
            protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                request.getRequestDispatcher("/stores.jsp").include(request, response);
            }

        }

        @WebServlet(name = "removegames", value = "/removegames")
        public static class RemoveGames extends HttpServlet {
            Store chosenStore = null;
            @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                request.setAttribute("stores", admin.getStores());

                String selectedStore = request.getParameter("selectedStore");
                if (selectedStore != null){
                    chosenStore = admin.getStores().get(Integer.parseInt(selectedStore));
                    request.setAttribute("selectedStoreIndex", Integer.parseInt(selectedStore));

                    request.setAttribute("selectedStore", chosenStore);
                }

                if(request.getParameter("removeGame") != null){
                    System.out.println(request.getParameter("removeGame"));
                    chosenStore.removeGame(Integer.parseInt(request.getParameter("removeGame")));
                }

//                String gameName = request.getParameter("gameName");
//
//                if(gameName != null){
//                    chosenStore.addGame(gameName);
//                }


                request.getRequestDispatcher("/stores.jsp").include(request, response);
            }

            @Override
            protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                request.getRequestDispatcher("/stores.jsp").include(request, response);
            }

        }



    }

}
