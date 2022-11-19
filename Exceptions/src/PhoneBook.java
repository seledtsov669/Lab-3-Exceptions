import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class PhoneBook {
    private String number;

    public PhoneBook(String number) {
        this.number = number;
    }

    static ArrayList<Record> group = new ArrayList<>();

    public List<Record> getAllRecords() {
        return group;
    }


    public void createRecord(Record record) throws PhoneNumberAlreadyExists {
        for (Record i : group) {
            if (Objects.equals(i.getPhoneNumber(), record.getPhoneNumber())) {
                throw new PhoneNumberAlreadyExists("Номер телефона уже существует.", record.getPhoneNumber());
            }
        }
        group.add(record);
    }

    public void updateRecord(Record record) throws RecordNotFound, PhoneNumberAlreadyExists {
        long k = 0;
        for (Record i : group) {
            if (i.getId() == record.getId()) {
                k += 1;
                if (Objects.equals(i.getPhoneNumber(), record.getPhoneNumber())) {
                    i.setName(record.getName());
                }
                if (!Objects.equals(i.getPhoneNumber(), record.getPhoneNumber())) {
                    for (Record j : group) {
                        if (Objects.equals(j.getPhoneNumber(), record.getPhoneNumber())) {
                            throw new PhoneNumberAlreadyExists("Нельзя добавить номер телефона. Такой уже существует.", record.getPhoneNumber());
                        }
                    }
                    i.setName(record.getName());
                    i.setPhoneNumber(record.getPhoneNumber());
                }
            }
        }
        if (k == 0) {
            throw new RecordNotFound("Нельзя добавить номер телефона. Такой id не найден.", record.getId());
        }
    }


    public void deleteRecord(long id) throws RecordNotFound {
        long k = 0;
        for (int i = 0; i < group.size(); i++) {
            if (group.get(i).getId() == id) {
                k += 1;
                group.remove(i);
            }
        }
        if (k == 0) {
            throw new RecordNotFound("Нельзя удалить запись. Не существует записи с id: ", id);
        }
    }
}