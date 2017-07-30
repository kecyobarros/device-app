package br.com.kecyo.deviceapp.utils;

import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AjusteBaseCSV {

    private Function<String, String> mapToItem = (line) -> {
        String[] split = line.split(";");
        String[] newValue = new String[20];

        int indexNewArray = 0;

        for(int index = 0; index < split.length; index++){

            TypeColumnCSV typeColumnCSV = TypeColumnCSV.findByIndex(indexNewArray);

            try{
                typeColumnCSV.valid(split[index].trim());
                newValue[indexNewArray] = split[index];
            }catch (Exception ex){
                newValue[--indexNewArray] += split[index];
            }
            indexNewArray++;
        }

        return StringUtils.arrayToDelimitedString(newValue, ";");
    };

    public void execute() throws URISyntaxException, IOException {
        final File inputF = new File(AjusteBaseCSV.class.getResource("/load/loadDevices.csv").toURI());
        final InputStream inputFS = new FileInputStream(inputF);
        final BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

        List<String> collect = br.lines()
                .map(mapToItem)
                .collect(Collectors.toList());

        Files.write(Paths.get("load/loadDevices.csv"), collect,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

    }
}
