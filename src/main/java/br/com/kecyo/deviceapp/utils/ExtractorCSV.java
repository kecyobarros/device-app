package br.com.kecyo.deviceapp.utils;

import br.com.kecyo.deviceapp.entities.*;
import com.google.common.collect.Sets;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ExtractorCSV {

    private String[] columns;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private Function<String, Device> mapToItem = (line) -> {
        columns = line.split(";");
        return Device.builder()
                .id(getValue(TypeColumnCSV.INDEX_DEVICE_ID.getIndex()))
                .systemOS(createSystemOS())
                .model(getValue(TypeColumnCSV.INDEX_DEVICE_MODEL.getIndex()))
                .home(createHome())
                .work(createWork())
                .appsInstalled(createInstalledApps())
                .visits(createVisit())
                .build();
    };


    public Map<String, Optional<Device>> execute() throws URISyntaxException, IOException {

        Map<String, Optional<Device>> collect;

        final File inputF = new File(ExtractorCSV.class.getResource("/load/loadDevices.csv").toURI());
        final  InputStream inputFS = new FileInputStream(inputF);
        final BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

        collect = br.lines()
                .map(mapToItem)
                .collect(Collectors.groupingBy(Device::getId,
                        Collectors.reducing((device1, device2) -> {

                            device1.getAppsInstalled().addAll(device2.getAppsInstalled());
                            device1.getVisits().addAll(device2.getVisits());
                            return device1;
                        }))
                );

        br.close();

        return collect;
    }

    private SystemOS createSystemOS(){
        return new SystemOS(getValue(TypeColumnCSV.INDEX_OS_NAME.getIndex()),
                getValue(TypeColumnCSV.INDEX_OS_VERSION.getIndex()));
    }

    private Home createHome(){
        final String homeLongLati = getValue(TypeColumnCSV.INDEX_HOME.getIndex());

        if (StringUtils.isEmpty(homeLongLati)){
            return null;
        }

        String[] coordinates = homeLongLati.split(" ");
        return new Home(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));

    }

    private Work createWork(){
        final String homeLongLati = getValue(TypeColumnCSV.INDEX_WORK.getIndex());

        if (StringUtils.isEmpty(homeLongLati)){
            return null;
        }

        String[] coordinates = homeLongLati.split(" ");
        return new Work(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));

    }

    private Set<String> createInstalledApps() {
        final String installedApps = getValue(TypeColumnCSV.INDEX_INSTALLED_APPS.getIndex());

        if (StringUtils.isEmpty(installedApps)){
            return Sets.newHashSet();
        }

        return Sets.newHashSet(installedApps.split("\\|"));
    }

    private List<Visit> createVisit() {
        ArrayList<Visit> visits = new ArrayList<>();
        visits.add(Visit.builder()
                    .batteryState((getValue(TypeColumnCSV.INDEX_BATTERY_STATE.getIndex()) == null ? 0 : Integer.parseInt(getValue(TypeColumnCSV.INDEX_BATTERY_STATE.getIndex()))))
                    .batteryPercentage((getValue(TypeColumnCSV.INDEX_BATTERY_PERCENTAGE.getIndex()) == null ? 0 : Integer.parseInt(getValue(TypeColumnCSV.INDEX_BATTERY_PERCENTAGE.getIndex()))))
                    .arrival(LocalDateTime.parse(getValue(TypeColumnCSV.INDEX_ARRIVAL.getIndex()), formatter))
                    .departure(LocalDateTime.parse(getValue(TypeColumnCSV.INDEX_DEPARTURE.getIndex()), formatter))
                    .categorie(getValue(TypeColumnCSV.INDEX_CATAGORIE.getIndex()))
                    .venue(createVenue())
                    .build());

        return visits;
    }

    private Venue createVenue(){
        return Venue.builder()
                .name(getValue(TypeColumnCSV.INDEX_VENUE_NAME.getIndex()))
                .totalTime((getValue(TypeColumnCSV.INDEX_VENUE_TOTAL_TIME.getIndex()) == null ? 0 : Integer.parseInt(getValue(TypeColumnCSV.INDEX_VENUE_TOTAL_TIME.getIndex()))))
                .precision((getValue(TypeColumnCSV.INDEX_PRECISION.getIndex()) == null ? 0 : Integer.parseInt(getValue(TypeColumnCSV.INDEX_PRECISION.getIndex()))))
                .longitude(getLongLati(true))
                .latitude(getLongLati(false))
                .address(getValue(TypeColumnCSV.INDEX_ADDRESS.getIndex()))
                .city(getValue(TypeColumnCSV.INDEX_CITY.getIndex()))
                .state(getValue(TypeColumnCSV.INDEX_STATE.getIndex()))
                .country(getValue(TypeColumnCSV.INDEX_COUNTRY.getIndex()))
                .build();
    }

    private double getLongLati(boolean longitude){

        String value = getValue(TypeColumnCSV.INDEX_VENUE_LONG_LAT.getIndex());

        if (StringUtils.isEmpty(value) || value.equalsIgnoreCase("none")){
            return 0;
        }

        String[] coordinates = value.split(" ");

        if(longitude){
            return Double.parseDouble(coordinates[0]);
        }

        return Double.parseDouble(coordinates[1]);
    }

    private String getValue(final int index){
        final String result = columns[index].trim();
        return (result.equals("null")?null:result);
    }
}
