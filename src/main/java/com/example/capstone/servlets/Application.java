package com.example.capstone.servlets;

import com.example.capstone.beans.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import com.example.capstone.beans.Store;
import com.example.capstone.beans.StoreBuilder;
import com.example.capstone.dao.ApplicationDao;

import static java.lang.Long.parseLong;

/**
  * This class contains all the servlets for the main application.
  * List of servlets: home, stores, addstores, editstores, removestores, addgames, removegames
  **/
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

            //Sending list of stores to jsp obtained from the admin object.
            request.setAttribute("stores", admin.getStores());

            //Check if there is a store selected from the list.
            String selectedStore = request.getParameter("selectedStore");

            if (selectedStore != null){
                Store chosenStore = admin.getStores().get(Integer.parseInt(selectedStore));
                request.setAttribute("selectedStoreIndex", Integer.parseInt(selectedStore));
                request.setAttribute("selectedStore", chosenStore);
            }

            request.getRequestDispatcher("/stores.jsp").include(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            request.getRequestDispatcher("/stores.jsp").include(request, response);
        }

        @WebServlet(name = "addstores", value = "/addstores")
        public static class AddStores extends HttpServlet {

            @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                String storeName = request.getParameter("storeName");
                String storeAddress = request.getParameter("storeAddress");

                if(storeName != null && storeAddress != null){

                    //Create bean with added store information.
                    Store addedStore = new StoreBuilder().setStoreName(storeName).setStoreAddress(storeAddress).createStore();

                    //Update database with added store
                    ApplicationDao dao = new ApplicationDao();
                    dao.addStoreToDatabase(addedStore);

                    //Retrieve created Store ID and add to store object
                    addedStore.setStoreId(dao.retrieveStoreID(addedStore));

                    //add store to Admin object
                    admin.addStore(addedStore);
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

                //Send stores to jsp
                request.setAttribute("stores", admin.getStores());

                //retrieve selected Store to modify
                String selectedStore = request.getParameter("selectedStore");
                if (selectedStore != null){
                    chosenStore = admin.getStores().get(Integer.parseInt(selectedStore));
                    request.setAttribute("selectedStoreIndex", Integer.parseInt(selectedStore));
                    request.setAttribute("selectedStore", chosenStore);
                }

                String storeName = request.getParameter("storeName");
                String storeAddress = request.getParameter("storeAddress");

                long storeId;
                if (request.getParameter("id") != null) {
                    storeId = Long.parseLong(request.getParameter("id"));
                } else {
                    storeId = -1;
                }



                if(storeName != null && storeAddress != null){
                    Store fetchedStore = admin.getStoreById(storeId);
                    if(fetchedStore != null){
                        fetchedStore.setStoreName(storeName);
                        fetchedStore.setStoreAddress(storeAddress);
                        fetchedStore.setStoreId(storeId);

                        //Update database
                        ApplicationDao dao = new ApplicationDao();
                        dao.updateStoreInDB(fetchedStore);
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

                //Send stores to jsp
                request.setAttribute("stores", admin.getStores());

                //retrieve selected Store to modify
                String selectedStore = request.getParameter("selectedStore");

                if (selectedStore != null){
                    chosenStore = admin.getStores().get(Integer.parseInt(selectedStore));
                    request.setAttribute("selectedStoreIndex", Integer.parseInt(selectedStore));
                    request.setAttribute("selectedStore", chosenStore);
                }

                if(request.getParameter("removeStore") != null && chosenStore != null){
                    //remove store from the database then remove from the admin object
                    ApplicationDao dao = new ApplicationDao();
                    dao.removeStoreFromDatabase(chosenStore);
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

                //Send stores to jsp
                request.setAttribute("stores", admin.getStores());

                //retrieve selected Store to modify
                String selectedStore = request.getParameter("selectedStore");

                if (selectedStore != null){
                    chosenStore = admin.getStores().get(Integer.parseInt(selectedStore));
                    request.setAttribute("selectedStoreIndex", Integer.parseInt(selectedStore));
                    request.setAttribute("selectedStore", chosenStore);
                }

                String gameName = request.getParameter("gameName");

                if(gameName != null){
                    //add game to store in database
                    ApplicationDao dao = new ApplicationDao();
                    dao.addGameToStore(gameName, chosenStore);
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

                //Send stores to jsp
                request.setAttribute("stores", admin.getStores());

                //retrieve selected Store to modify
                String selectedStore = request.getParameter("selectedStore");

                if (selectedStore != null){
                    chosenStore = admin.getStores().get(Integer.parseInt(selectedStore));
                    request.setAttribute("selectedStoreIndex", Integer.parseInt(selectedStore));
                    request.setAttribute("selectedStore", chosenStore);
                }

                if(request.getParameter("removeGame") != null){
                    //remove game from store in DB
                    ApplicationDao dao = new ApplicationDao();
                    dao.removeGameFromStore(request.getParameter("removeGame"), chosenStore);
                    chosenStore.removeGame(request.getParameter("removeGame"));
                }

                request.getRequestDispatcher("/stores.jsp").include(request, response);
            }

            @Override
            protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                request.getRequestDispatcher("/stores.jsp").include(request, response);
            }

        }
    }
}