
from TVproject import TVproject
from Tv import Tv

tv_list = list()

class app(object):
    def show_menu():
        print("========메뉴를 선택하시오=========")
        print("========1.입력=========")
        print("========2.출력=========")
        print("========3.검색======")
        print("========4.종료========")
    def insert_tv_info():
        try:
            name = input("장비명 : ")
            qty = input("수량 : ")
            date = input("생산일 : ")
            tv_list.append(Tv(name, qty, date))
            print ("계속 입력하시겠습니까?")
            
    def show_tv_list():
        try:
            for i in range(len(tv_list)):
                    print(tv_list[i].name, tv_list[i].qty, tv_list[i].date)
                    i +=1
            print("========장비목록 출력 완료=========")
            input()
    def search_tv_info():
        try:
            name = input("검색할 장비명을 입력하세요 : ")

    def cls_stu_info():
