package library.entities;

public enum PressParameters {
    title,
    author,
    dateOfCreate,
    type,
    genre,
    length,
    absolutePath;

    public String toString(PressParameters pressParameter) {
        if (pressParameter == title) {
            return "Название";
        } else if (pressParameter == author) {
            return "Автор";
        } else if (pressParameter == dateOfCreate) {
            return "Дата создания";
        } else if (pressParameter == type) {
            return "Тип";
        } else if (pressParameter == genre) {
            return "Жанр";
        } else if (pressParameter == length) {
            return "Длина";
        } else if (pressParameter == absolutePath) {
            return "Путь к файлу";
        } else {
            return "";
        }
    }
}
