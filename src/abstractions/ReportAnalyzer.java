package abstractions;

/**
 * Абстрактный класс, предназначенный для расчета всей математики в отчетах
 */
public abstract class ReportAnalyzer {

    /**
     * Абстрактный метод для нахождения суммы всех доходов
     *
     * @return сумма доходов
     */
    public abstract int getSumRentable();

    /**
     * Абстрактный метод для нахождения суммы всех трат
     *
     * @return сумма трат
     */
    public abstract int getSumCostly();

    /**
     * Абстрактный метод для нахождения самого выгодного объекта (товар, месяц и .д.)
     *
     * @return возвращает название и сумму
     */
    public abstract String getRentable();

    /**
     * Абстрактный метод для нахождения самого расходного объекта (товар, месяц и .д.)
     *
     * @return возвращает название и сумму
     */
    public abstract String getCostly();
}
