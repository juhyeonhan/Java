class Tv(object):
    def __init__(self, name, qty, date):
        self.name = name
        self.qty = qty
        self.date = date
        print("__init__called")