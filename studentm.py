#pyinstaller --onefile --windowed --icon=youricon.ico yourprogram.py NOTE (how to make your program an excecutable file)

from tkinter import*
from tkinter import ttk
import pymysql
from tkinter import messagebox


textvariable=''
class Student:
    def __init__(self,root):
        self.root = root
        self.root.title("Student Management System.. Designed by Ethom Digital")
        self.root.geometry("1350x700+0+0")

        title = Label(self.root, text="Student Management System", bd=10, relief=GROOVE, font =("times new roman", 40, 'bold'), bg="blue", fg="yellow")
        title.pack(side=TOP, fill=X)

        # ************ All Variables **************
        self.matric_var=StringVar()
        self.firstname_var = StringVar()
        self.lastname_var = StringVar()
        self.level_var = StringVar()
        self.department_var = StringVar()
        self.email_var = StringVar()
        self.phone_var = StringVar()
        self.address_var = StringVar()

        self.search_by = StringVar()
        self.search_txt = StringVar()

        #************ Manage Frame **************

        manage_frame = Frame(self.root, bd =4, relief = RIDGE, bg="gold")
        manage_frame.place(x=20, y=100, width=450, height=600)

        m_title=Label(manage_frame, text="Manage Students", bg="gold", fg="blue", font=("times new roman", 30, "bold"))
        m_title.grid(row=0, columnspan=2,pady=20)

        # ************ Registration label and Entry **************
        lbl_matric = Label(manage_frame, text="Matric Number", bg = "gold", fg="blue", font=("times new roman", 15, "bold"))
        lbl_matric.grid(row=1, column=0, pady=10, padx=20, sticky="w")

        txt_matric = Entry(manage_frame, textvariable = self.matric_var, font=("times new roman", 15, "bold"), bd=5, relief=GROOVE)
        txt_matric.grid(row=1, column=1, pady=10, padx=20, sticky="w")

        lbl_first_name = Label(manage_frame, text="First Name", bg="gold", fg="blue", font=("times new roman", 15, "bold"))
        lbl_first_name.grid(row=2, column=0, pady=10, padx=20, sticky="w")

        txt_first_name = Entry(manage_frame, textvariable = self.firstname_var, font=("times new roman", 15, "bold"), bd=5, relief=GROOVE)
        txt_first_name.grid(row=2, column=1, pady=10, padx=20, sticky="w")

        lbl_last_name = Label(manage_frame, text="Last Name", bg="gold", fg="blue", font=("times new roman", 15, "bold"))
        lbl_last_name.grid(row=3, column=0, pady=10, padx=20, sticky="w")

        txt_last_name = Entry(manage_frame, textvariable = self.lastname_var, font=("times new roman", 15, "bold"), bd=5, relief=GROOVE)
        txt_last_name.grid(row=3, column=1, pady=10, padx=20, sticky="w")

       # lbl_gender = Label(manage_frame, text="Gender", bg="gold", fg="blue", font=("times new roman", 15, "bold"))
       # lbl_gender.grid(row=4, column=0, pady=10, padx=20, sticky="w")

       # combo_gender=ttk.Combobox(manage_frame, font=("times new roman", 15, "bold"), state='readonly')
        #combo_gender['values'] = ("Male", "Female", "Other")
       # combo_gender.grid(row=4, column=1, padx=20, pady=10)

        lbl_level = Label(manage_frame, text="Level", bg="gold", fg="blue", font=("times new roman", 15, "bold"))
        lbl_level.grid(row=5, column=0, pady=10, padx=20, sticky="w")

        combo_level = ttk.Combobox(manage_frame, textvariable = self.level_var, font=("times new roman", 14, "bold"), state='readonly')
        combo_level['values'] = ("ND 1", "ND 2 ", "HND 1", "HND 2", "Others")
        combo_level.grid(row=5, column=1, padx=20, pady=10)

        lbl_department = Label(manage_frame, text="Department", bg="gold", fg="blue",
                               font=("times new roman", 15, "bold"))
        lbl_department.grid(row=6, column=0, pady=10, padx=20, sticky="w")

        combo_department = ttk.Combobox(manage_frame, textvariable = self.department_var, font=("times new roman", 15, "bold"), state='readonly')
        combo_department['values'] = (
        "Computer Science", "Computer Engineering", "Mass Communication", "Business Administration",
        "Hospitality Management", "Others")
        combo_department.grid(row=6, column=1, padx=20, pady=10)


        lbl_email = Label(manage_frame, text="Email", bg="gold", fg="blue", font=("times new roman", 15, "bold"))
        lbl_email.grid(row=7, column=0, pady=10, padx=20, sticky="w")

        txt_email = Entry(manage_frame, textvariable = self.email_var, font=("times new roman", 15, "bold"), bd=5, relief=GROOVE)
        txt_email.grid(row=7, column=1, pady=10, padx=20, sticky="w")

        lbl_phone_no = Label(manage_frame, text="Phone Number", bg="gold", fg="blue", font=("times new roman", 15, "bold"))
        lbl_phone_no.grid(row=8, column=0, pady=10, padx=20, sticky="w")

        txt_phone_no = Entry(manage_frame, textvariable = self.phone_var, font=("times new roman", 15, "bold"), bd=5, relief=GROOVE)
        txt_phone_no.grid(row=8, column=1, pady=10, padx=20, sticky="w")

        lbl_address = Label(manage_frame, text="Address", bg="gold", fg="blue", font=("times new roman", 15, "bold"))
        lbl_address.grid(row=9, column=0, pady=10, padx=20, sticky="w")

        self.txt_address = Text(manage_frame, width =30, height = 3, font=("times new roman", 10, "bold"), bd=5, relief=GROOVE)
        self.txt_address.grid(row=9, column=1, pady=10, padx=20, sticky="w")

        # ************ Manage Button Frame **************
        button_frame = Frame(manage_frame, bd=4, relief=RIDGE, bg="gold")
        button_frame.place(x=15, y=535, width=410)

        add_button = Button(button_frame, text="Add", width=10, command = self.add_students).grid(row=0, column=0, padx=10, pady=10)
        update_button = Button(button_frame, text="Update", width=10, command = self.update_data).grid(row=0, column=1, padx=10, pady=10)
        delete_button = Button(button_frame, text="Delete", width=10, command = self.delete_data).grid(row=0, column=2, padx=10, pady=10)
        clear_button = Button(button_frame, text="Clear", width=10,command = self.clear ).grid(row=0, column=3, padx=10, pady=10)


        # ************ Manage Detailed Frame **************
        detail_manage_frame = Frame(self.root, bd=4, relief=RIDGE, bg="gold")
        detail_manage_frame.place(x=500, y=100, width=800, height=660)

        label_search = Label(detail_manage_frame, text="Search By", bg="gold", fg="blue", font=("times new roman", 20, "bold"))
        label_search.grid(row=0,column=0, pady=10, padx=20, sticky="w")

        combo_search = ttk.Combobox(detail_manage_frame, width=13, textvariable =self.search_by,  font=("times new roman", 11, "bold"), state='readonly')
        combo_search['values'] = ("Matric Number", "Phone Number")
        combo_search.grid(row=0, column=1, padx=20, pady=10)

        text_search = Entry(detail_manage_frame, width=15, textvariable =self.search_txt, font=("times new roman", 10, "bold"), bd=5, relief=GROOVE)
        text_search.grid(row=0, column=2, pady=10, padx=20, sticky="w")

        search_button = Button(detail_manage_frame, text= "Search", width=10, pady=5, command = self.search_data).grid(row=0,column=3, padx=10, pady=10)
        show_all_button = Button(detail_manage_frame, text="Show All", width=10, pady=5, command = self.fetch_data).grid(row=0, column=4, padx=10, pady=10)


        # ************ Table Frame **************
        table_frame = Frame(detail_manage_frame, bd=4, relief=RIDGE, bg="gold")
        table_frame.place(x=10, y=70, width=760, height= 500)

        scroll_x = Scrollbar(table_frame, orient=HORIZONTAL)
        scroll_y = Scrollbar(table_frame, orient=VERTICAL)
        self.student_table = ttk.Treeview(table_frame,  columns=("matric", "firstname", "lastname", "level", "department",
                "email", "phone", "address"), xscrollcommand = scroll_x.set, yscrollcommand=scroll_y.set)

        scroll_x.pack(side = BOTTOM, fill=X)
        scroll_y.pack(side = RIGHT, fill=Y)
        scroll_x.config(command=self.student_table.xview)
        scroll_y.config(command=self.student_table.yview)
        self.student_table.heading("matric", text="Matric Number.")
        self.student_table.heading("firstname", text="First Name.")
        self.student_table.heading("lastname", text="Last Name.")
        self.student_table.heading("level", text="Level.")
        self.student_table.heading("department", text="Department.")
        self.student_table.heading("email", text="Email.")
        self.student_table.heading("phone", text="Phone Number.")
        self.student_table.heading("address", text="Address.")
        self.student_table['show'] = 'headings'
        self.student_table.column("matric", width=100)
        self.student_table.column("firstname", width=100)
        self.student_table.column("lastname", width=100)
        self.student_table.column("level", width=100)
        self.student_table.column("department", width=100)
        self.student_table.column("email", width=100)
        self.student_table.column("phone", width=100)
        self.student_table.column("address", width=150)
        self.student_table.pack(fill = BOTH, expand=1)
        self.student_table.bind("<ButtonRelease-1>", self.get_cursor)


        self.fetch_data()

    # ************ Save student information into database **************
    def add_students(self):
        if self.matric_var.get()=="" or self.firstname_var.get()=="":
            messagebox.showerror("Eror", "All fields aer required!!!")

        else:
            con = pymysql.connect(host="localhost", user = "root", password = "ethomdigital", database = "manage_student")
            cur = con.cursor()
            cur.execute("insert into studentss values(%s, %s, %s, %s, %s, %s, %s, %s)", (self.matric_var.get(),
                                                                                        self.firstname_var.get(),
                                                                                        self.lastname_var.get(),
                                                                                        self.level_var.get(),
                                                                                        self.department_var.get(),
                                                                                        self.email_var.get(),
                                                                                        self.phone_var.get(),
                                                                                        self.txt_address.get('1.0', END)))
            con.commit()
            self.clear()
            con.close()
            messagebox.showinfo("Success", "Record has been inserted")

    def fetch_data(self):
        con = pymysql.connect(host="localhost", user="root", password="ethomdigital", database="manage_student")
        cur = con.cursor()
        cur.execute("select * from studentss")
        rows = cur.fetchall()
        if len(rows) != 0:
            self.student_table.delete(*self.student_table.get_children())
            for row in rows:
                self.student_table.insert('', END, values =row)
            con.commit()
        con.close()

    def clear(self):
        (self.matric_var.set(""),
         self.firstname_var.set(""),
         self.lastname_var.set(""),
         self.level_var.set(""),
         self.department_var.set(""),
         self.email_var.set(""),
         self.phone_var.set(""),
         self.txt_address.delete('1.0', END))

    def get_cursor(self, ev):
        cursor_row = self.student_table.focus()
        contents = self.student_table.item(cursor_row)
        row = contents['values']
        self.matric_var.set(row[0]),
        self.firstname_var.set(row[1]),
        self.lastname_var.set(row[2]),
        self.level_var.set(row[3]),
        self.department_var.set(row[4]),
        self.email_var.set(row[5]),
        self.phone_var.set(row[6]),
        self.txt_address.delete('1.0', END)
        self.txt_address.insert(END, row[7])

    def update_data(self):
        con = pymysql.connect(host="localhost", user="root", password="ethomdigital", database="manage_student")
        cur = con.cursor()
        cur.execute("update studentss SET matric = %s, firstname = %s, lastname = %s, level = %s, department = %s, email = %s, phone =  %s, address = %s", (
                                                                                     self.firstname_var.get(),
                                                                                     self.lastname_var.get(),
                                                                                     self.level_var.get(),
                                                                                     self.department_var.get(),
                                                                                     self.email_var.get(),
                                                                                     self.phone_var.get(),
                                                                                     self.txt_address.get('1.0', END),
                                                                                     self.matric_var.get()))

        con.commit()
        self.fetch_data()
        self.clear()
        con.close()

    def delete_data(self):
        con = pymysql.connect(host="localhost", user="root", password="ethomdigital", database="manage_student")
        cur = con.cursor()


        cur.execute("insert into recycle_bin values(%s, %s, %s, %s, %s, %s, %s, %s)", (self.matric_var.get(),
                                                                                     self.firstname_var.get(),
                                                                                     self.lastname_var.get(),
                                                                                     self.level_var.get(),
                                                                                     self.department_var.get(),
                                                                                     self.email_var.get(),
                                                                                     self.phone_var.get(),
                                                                                     self.txt_address.get('1.0', END)))
        cur.execute("delete from studentss where matric =%s", self.matric_var.get())
        con.commit()
        self.clear()
        self.fetch_data()
        con.close()
        messagebox.showinfo("Success", "Record has been Deleted! You can retrieve it back from Recycle bin Table")

    def search_data(self):
        con = pymysql.connect(host="localhost", user="root", password="", database="manage_student")
        cur = con.cursor()

        cur.execute("select * from studentss where" +str(self.search_by.get())+" LIKE '%" +str(self.search_txt.get())+"%'")
        rows = cur.fetchall()
        if len(rows) != 0:
            self.student_table.delete(*self.student_table.get_children())
            for row in rows:
                self.student_table.insert('', END, values =row)
            con.commit()
        con.close()










root = Tk()
ob = Student(root)
root.iconbitmap(r'C:\Users\USER\PycharmProjects\Students\ETHOM.ico')
root.mainloop()

