package com.example.CarShop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarRepository {

    public List<Car> getCarList() {
        return getCars();
    }

    private List<Car> carList = new ArrayList<>();
        public CarRepository()
        {
            addCar(new Car("SAAB","9-5", 10000, 1999, 7000, "Soltak, automat", "http://lh4.ggpht.com/-C41mB8UNUAs/UPCxFdbGQWI/AAAAAAAAlcg/TpktfNFbgDc/s1600/2004-saab-9-5-sedan-00011.jpg"));
            addCar(new Car("Volvo", "V70", 8000, 2012, 95000, "En ägare", "http://st.motortrend.com/uploads/sites/10/2015/11/2009-volvo-v70-3.2-wagon-side-view.png"));
            addCar(new Car("Koeningsegg", "1", 2000, 2018, 700000, "UNIK!", "http://i0.wp.com/www.koenigsegg.com/wp-content/uploads/2014/11/DSC_2422.jpg"));
            addCar(new Car("BMW", "320", 10000, 2001, 80000, "Rostig", "https://imgsvrcars-a.akamaihd.net/images/2017/3/BMW-320d-GT/x.jpg/BMW-320dGT-8.jpg"));
            addCar(new Car("Audi", "A6", 10000, 1999, 7000, "Fint skick", "https://images.hgmsites.net/hug/2019-audi-a6_100678998_h.jpg"));
        }
        public void addCar(Car car) {
            carList.add(car);
        }

    public int getNumberOfCarsInStore() {
        return carList.size();
    }

    public void sortCarsByPrice() {
        Collections.sort(carList, Car::compareTo);
    }

/*        public List<Car> getCars() {
            return carList;
        }
*/
       public List<Car> getCars() {
        List<Car> filterList = new ArrayList<>();
           for (Car aCar: carList) {
               if (aCar.isSold() == false)
                   filterList.add(aCar);
           }
        return filterList;
        }

    public Car getCar(int index) {
        List<Car> templist = getCars();
        return templist.get(index);
    }

    public boolean carInStore(String brand) {
        for (Car car : carList) {
            if (car.getBrand() == brand) {
                return true;
            }
        }
        return false;
    }
    public void replace(int pos, Car car)
    {
        Car loopcar;
        int localpos = 0;
        for (int i=0; i< carList.size(); i++)   {
            loopcar = carList.get(i);
            if (pos == localpos)   {
                carList.remove(i);
                break;
            }
            if (loopcar.isSold() == false) {
                localpos++;
            }
        }
        carList.add(car);
    }
}

