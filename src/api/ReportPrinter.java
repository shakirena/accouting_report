package api;

/**
 * Интерфейс для печати отчетов
 * Реализации определяют тип печати (на консоль, файл и т.д)
 */
public interface ReportPrinter {
    public abstract void print();
}
