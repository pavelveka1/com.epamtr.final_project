package by.epamtr.airline.controller;

public class ConstantController {
	
	public static class PathToPage{
		public static final String PATH_TO_LOGIN_PAGE="/WEB-INF/jsp/login_page.jsp";
		public static final String PATH_TO_ADD_AIRCRAFT = "/WEB-INF/jsp/administrator_action/add_aircraft.jsp";
		public static final String PATH_TO_MAIN_PAGE="/WEB-INF/jsp/main_page.jsp";
		public static final String PATH_TO_ADD_AIRCRAFT_TYPE="/WEB-INF/jsp/administrator_action/add_aircraft_type.jsp";
		public static final String PATH_TO_ADD_CREW_TO_FLIGHT = "/WEB-INF/jsp/dispatcher_action/add_crew_item_to_flight.jsp";
		public static final String PATH_TO_CREW_BY_FLIGHT = "/WEB-INF/jsp/administrator_action/crew_by_flight.jsp";
		public static final String PATH_TO_ADD_FLIGHT = "/WEB-INF/jsp/administrator_action/add_flight.jsp";
		public static final String PATH_TO_ADD_USER_PAGE="/WEB-INF/jsp/administrator_action/add_user.jsp";
		public static final String PATH_TO_CHANGE_AIRCRAFT_STATUS = "/WEB-INF/jsp/administrator_action/change_aircraft_status.jsp";
		public static final String PATH_TO_DELETE_FLIGHT = "/WEB-INF/jsp/administrator_action/delete_flight.jsp";
		public static final String PATH_TO_DELETE_AIRCRAFT = "/WEB-INF/jsp/administrator_action/delete_aircraft.jsp";
		public static final String PATH_TO_DELETE_AIRCRAFT_TYPE = "/WEB-INF/jsp/administrator_action/delete_aircraft_type.jsp";
		public static final String PATH_TO_GET_CREW_BY_FLIGHT = "/WEB-INF/jsp/administrator_action/crew_by_flight.jsp";
		public static final String PATH_TO_UPDATE_FLIGHT = "/WEB-INF/jsp/dispatcher_action/update_flights.jsp";
		public static final String PATH_TO_GET_USERS_BY_ROLE = "/WEB-INF/jsp/user_action/show_users_by_role.jsp";
		public static final String PATH_FLIGHTS_BY_STATUS = "/WEB-INF/jsp/user_action/get_flights_by_status.jsp";
		public static final String PATH_TO_USERS_BY_ROLE = "/WEB-INF/jsp/user_action/users_by_role.jsp";
		public static final String PATH_TO_FLIGHTS_BY_USER = "/WEB-INF/jsp/user_action/flights_by_user.jsp";
		public static final String PATH_TO_GET_USERS_BY_FLIGHT = "/WEB-INF/jsp/administrator_action/users_by_flight.jsp";
		public static final String PATH_TO_GET_CREW_BY_FLIGHT_FOR_CREW = "/WEB-INF/jsp/user_action/crew_by_flight_data.jsp";
		public static final String ADMIN_PATH_TO_GET_USERS_BY_ROLE = "/WEB-INF/jsp/administrator_action/users_by_role.jsp";
		public static final String FLIGHTS_BY_STATUS_PAGE="/WEB-INF/jsp/user_action/get_flights_by_status.jsp";
		public static final String PATH_TO_CONTROLLER = "/Controller?command=GO_TO_LOGIN_PAGE";
		public static final String GO_TO_MAIN_PAGE_COMMAND = "/Controller?command=GO_TO_MAIN_PAGE";
		public static final String PATH_TO_UPDATE_AIRCRAFT = "/WEB-INF/jsp/administrator_action/update_aircraft.jsp";
		public static final String PATH_TO_UPDATE_FLIGHT_DATA = "/WEB-INF/jsp/administrator_action/update_flight_data.jsp";
		public static final String PATH_TO_UPDATE_USER_PAGE = "/WEB-INF/jsp/administrator_action/update_user.jsp";
	}
	
	
	public static class Parameter{
		public static final String REGISTER_NUMBER_PARAM = "register_number";
		public static final String AIRCRAFT_STATUS_PARAM = "aircraft_status";
		public static final String AIRCRAFT_TYPE="aircraft_type";
		public static final String RANGE_FLIGHT="range_flight";
		public static final String NUMBER_PASSENGERS="number_passengers";
		public static final String SELECTED_POSITION = "position";
		public static final String SELECTED_USER = "selected_user";
		public static final String FLIGHT_ID = "flight_id";
		public static final String CURRENT_CITY = "current_city";
		public static final String DESTINATION_CITY = "destination_city";
		public static final String FLIGHT_RANGE = "flight_range";
		public static final String FLIGHT_TIME = "flight_time";
		public static final String TIME_DEPARTURE = "time_departure";
		public static final String STATUS = "flight_status";
		public static final String EMAIL = "e_mail";
		public static final String NAME = "name";
		public static final String SURNAME = "surname";
		public static final String PATRONIMIC = "patronimic";
		public static final String ROLE = "role";
		public static final String PASSWORD = "password";
		public static final String LOGIN = "login";
		public static final String PAGE="page";
		public static final String ID_AIRCRAFT="id_aircraft";
		public static final String AIRCRAFT_STATUS="aircraft_status";
		public static final String NEW_STATUS = "flight_new_status";
		public static final String ID_FLIGHT = "id_flight";
		public static final String AIRCRAFT_NUMBER="aircraft_numbers";
		public static final String DELETE_AIRCRAFT_TYPE="delete";
		public static final String USER_ID="id_selected_user";
		public static final String ID_USER="id_user";
		public static final String ID_FLIGHT_PARAM = "radio_id_flight";
		public static final String USER_ROLE = "role";
		public static final String FLIGHT_STATUS = "flight_status";
		public static final String ADD_CREW_TO_FLIGHT="add_crew";
		public static final String FORM = "form";
		public static final String AIRCRAFT_NUMBER_PARAM="aircraft_numbers";
		public static final String FLIGHT_DATA_STATUS = "flight_data";
		public static final String CHANGES = "changes";
		
		
	}
	
	
	
	public static class Attribute{
		public static final String CURRENT_PAGE="current_page";
		public static final String ID_AIRCRAFT_TYPE = "id_iarcraft_type";
		public static final String AIRCRAFT_TYPES="aircraftTypes";
		public static final String RESULT_ATTR = "result_attr";
		public static final String SUCCESSFUL_OPERATION = "success";
		public static final String FAILED_OPERATION = "fail";
		public static final String FREE_USERS_BY_POSITION = "free_users_by_position";
		public static final String FREE_POSITIONS_BY_FLIGHT="free_positions";
		public static final String SELECTED_FLIGHT="selected_flight";
		public static final String TEAM_BY_FLIGHT="flight_team";
		public static final String FLIGHT_ID = "flight_id";
		public static final String SELECTED_POSITION="selected_position";
		public static final String ID_AIRCRAFT = "id_iarcraft";
		public static final String AIRCRAFTS="aircrafts";
		public static final String SELECTED_FLIGHT_STATUS_FOR_FLIGHTS = "selected_flight_status_attr";
		public static final String FLIGHT_STATUS = "flight_status_attr";
		public static final String FLIGHTS = "flights";
		public static final String SIGNED_IN_USER="user";
		public static final String DELETED_USER = "deleted_user";
		public static final String SELECTED_STATUS="selected_status";
		public static final String USERS_BY_ROLE="users_by_role";
		public static final String SELECTED_USER = "selected_user";
		public static final String SELECTED_ROLE="selected_role";
		public static final String USER_BY_ROLE="users_by_role";
		public static final String CURRENT_ROLE="current_role";
		public static final String SIGN_IN_FAIL_ATTR="sign_in_fail_attr";
		public static final String SIGN_IN_FAIL="sign_in_fail";
		public static final String USER_INFO = "user_info";
		public static final String ID_USER = "id_user_attr";
		public static final String DATA_IS_VALID="data_is_valid";
		public static final String FLIGHT_RANGE_VALID="flight_range_valid";
		public static final String PASSENGER_NUMBER_VALID="number_passengers_valid";
		public static final String AIRCRAFT_TYPE_VALID="type_valid";
		public static final String NAME_VALID="name_valid";
		public static final String SURNAME_VALID="surname_valid";
		public static final String PATRONIMIC_VALID="patronimic_valid";
		public static final String EMAIL_VALID="email_valid";
		public static final String LOGIN_VALID="login_valid";
		public static final String PASSWORD_VALID="password_valid";
		public static final String CURRENT_CITY_VALID="current_city_valid";
		public static final String DESTINATION_CITY_VALID="destination_city_valid";
		public static final String FLIGHT_TIME_VALID="flight_time_valid";
		public static final String TIME_DEPARTURE_VALID="time_daparture_valid";
		
	}

}
