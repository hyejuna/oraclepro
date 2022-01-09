package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	private static Scanner sc = new Scanner(System.in);
	private static PhoneDao pDao = new PhoneDao();
	
	public static void main(String[] args) {
		
		showTitle();
		
		boolean repeat = true;	
		while(repeat) {
			showMenu();
			String menuNo = sc.nextLine();
			
			switch(menuNo) {
				case "1" :
					showPersonList();
					break;
					
				case "2" : 
					insertPerson();
					break;
					
				case "3" : 
					updatePerson();
					break;
					
				case "4" : 
					deletePerson();
					break;
				
				case "5" : 
					searchPerson();
					break;	
					
				case "6" : 
					repeat = false;
					break;
					
				default : System.out.println("[다시 입력해 주세요.]");
				
			}
		}
		
		showEnd();
		sc.close();
		
	}

	// view
	private static void showTitle() {
		System.out.println("****************************************");
		System.out.println("*          전화번호 관리 프로그램            *");
		System.out.println("****************************************");
	}
	
	private static void showMenu() {
		System.out.println("\n1.리스트\t2.등록\t3.수정\t4.삭제\t5.검색\t6.종료");
		System.out.println("------------------------------------");
		System.out.print(">메뉴번호: ");
	}
	
	private static void showEnd() {
		System.out.println("\n****************************************");
		System.out.println("*               감사합니다                *");
		System.out.println("****************************************");
	}
	
	private static void showPersonList() {
		List<PersonVo> personList = pDao.personSelect();
		for(PersonVo vo : personList) 
			vo.showInfo();
	}

	private static void searchPerson() {
		System.out.println("<5.검색>");
		System.out.print(">검색어 : ");
		String key = sc.nextLine();
		
		List<PersonVo> personList = pDao.personSearch(key);
		for(PersonVo vo : personList) 
			vo.showInfo();
	}
	
	
	
	// manage
	private static void insertPerson() {
		System.out.println("<2.등록>");
		System.out.print(">이름: ");
		String name=sc.nextLine();
		System.out.print(">휴대전화: ");
		String phone=sc.nextLine();
		System.out.print(">회사전화: ");
		String company=sc.nextLine();
		
		PersonVo insertVo = new PersonVo(name, phone, company);
		pDao.personInsert(insertVo);
	}
	
	private static void updatePerson() {
		System.out.println("<3.수정>");
		System.out.print(">번호: ");
		int personId=sc.nextInt();
		sc.nextLine();
		System.out.print(">이름: ");
		String name=sc.nextLine();
		System.out.print(">휴대전화: ");
		String phone=sc.nextLine();
		System.out.print(">회사전화: ");
		String company=sc.nextLine();
		
		PersonVo updateVo = new PersonVo(personId, name, phone, company);
		pDao.personUpdate(updateVo);
	}
	
	private static void deletePerson() {
		System.out.println("<4.삭제>");
		System.out.print(">번호 : ");
		int delNo = sc.nextInt();
		sc.nextLine();
		
		pDao.personDelete(delNo);
	}
	
	
}