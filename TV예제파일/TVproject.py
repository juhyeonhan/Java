from app import app


while True:
    app.show_menu()
    insert_value =  int(input())
    if insert_value == 1:
        app.insert_tv_info()
    elif insert_value == 2:
        app.show_tv_list()
    elif insert_value == 3:
        app.search_tv_info()
    elif insert_value == 4:
        app.cls_stu_info()

