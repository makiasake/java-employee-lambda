package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;
// Teste git
public class Main {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter file path: ");
		String filePath = sc.next();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line = br.readLine();
			List<Employee> list = new ArrayList<>();

			while (line != null) {
				String[] data = line.split(",");
				list.add(new Employee(data[0], data[1], Double.parseDouble(data[2])));
				line = br.readLine();
			}

			System.out.print("Enter salary: ");
			double salary = sc.nextDouble();

			List<String> emails = list.stream().filter(e -> e.getSalary() > salary).map(e -> e.getEmail()).sorted()
					.collect(Collectors.toList());

			System.out.println("Email of employees whose salary is more than: " + String.format("%.2f", salary));
			emails.forEach(System.out::println);

			double sum = list.stream().filter(e -> e.getName().charAt(0) == 'M').map(e -> e.getSalary()).reduce(0.0,
					(x, y) -> x + y);
			System.out.println("Sum of salary from people whose name start with 'M': " + String.format("%.2f", sum));
		} catch (IOException e) {
			System.out.println("An error has ocurred: " + e.getMessage());
		}

		sc.close();
	}

}
