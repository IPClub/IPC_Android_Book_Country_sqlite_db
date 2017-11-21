package ipc.myapplication.database.country;

import java.util.List;

import ipc.myapplication.database.country.model.Country;

/**
 * Created by haykc on 11/21/2017.
 */

public interface CountryDB_I {
    void createCountry(Country c);
    List<Country> getAllCounties();
    Country getCountryById(long id);

}
