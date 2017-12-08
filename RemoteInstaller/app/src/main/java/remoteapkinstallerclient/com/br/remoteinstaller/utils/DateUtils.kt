package remoteapkinstallerclient.com.br.remoteinstaller.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

import java.util.Calendar.DAY_OF_MONTH

/**
 * Created by Eduardo on 14/01/2017.
 */

object DateUtils {
    private val months = arrayOf("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro")

    @Throws(Exception::class)
    fun toDate(format: String, dateToParse: String): Date {
        val simpleDateFormat = SimpleDateFormat(format)
        return simpleDateFormat.parse(dateToParse)
    }

    fun toDateAndZeroSeconds(timestamp: Long?): Date {
        val calendar = Calendar.getInstance()
        calendar.time = Date(timestamp!!)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    fun toDate(year: Int, month: Int, day: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = Date(System.currentTimeMillis())
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(DAY_OF_MONTH, day)
        return calendar.time
    }

    fun toDate(hour: Int, minute: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = Date(System.currentTimeMillis())
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        return calendar.time
    }

    fun today(): Date {
        val calendar = Calendar.getInstance()
        calendar.time = Date(System.currentTimeMillis())
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        return calendar.time
    }

    fun yesterday(): Date {
        val calendar = Calendar.getInstance()
        calendar.time = today()
        calendar.add(DAY_OF_MONTH, -1)
        return calendar.time
    }

    fun toString(format: String, dateToParse: Long): String {
        return toString(format, Date(dateToParse), "")
    }

    @JvmOverloads
    fun toString(format: String, dateToParse: Date, defaultVale: String = ""): String {
        try {
            val simpleDateFormat = SimpleDateFormat(format)
            //simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
            return simpleDateFormat.format(dateToParse)
        } catch (e: Exception) {
        }

        return defaultVale
    }

    fun getRemainingTime(remainingMillis: Long?): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 1)
        calendar.set(Calendar.SECOND, 0)
        calendar.add(Calendar.MILLISECOND, remainingMillis!!.toInt())
        return calendar.time
    }

    fun getMonthName(date: Date): String {
        //        months = context.getResources().getStringArray(R.array.months);
        val calendar = Calendar.getInstance()
        calendar.time = date
        val mes = calendar.get(Calendar.MONTH)
        return months[mes]
    }

    fun isThisMonth(date: Date): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = Date(System.currentTimeMillis())
        val thisMonth = calendar.get(Calendar.MONTH)
        val thisYear = calendar.get(Calendar.YEAR)
        calendar.time = date
        val dateMonth = calendar.get(Calendar.MONTH)
        val dateYear = calendar.get(Calendar.YEAR)
        return dateMonth == thisMonth && dateYear == thisYear
    }

    fun isThisDay(date: Date): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = Date(System.currentTimeMillis())
        val thisMonth = calendar.get(Calendar.MONTH)
        val thisYear = calendar.get(Calendar.YEAR)
        val thisDay = calendar.get(DAY_OF_MONTH)
        calendar.time = date
        val dateMonth = calendar.get(Calendar.MONTH)
        val dateYear = calendar.get(Calendar.YEAR)
        val dateDay = calendar.get(DAY_OF_MONTH)
        return dateMonth == thisMonth && dateYear == thisYear && dateDay == thisDay
    }

    fun initDate(date: Date): Date {
        val firstDay = Calendar.getInstance()
        firstDay.time = date
        firstDay.set(DAY_OF_MONTH, 1)
        return firstDay.time
    }

    fun finalDate(date: Date): Date {
        val lastDay = Calendar.getInstance()
        lastDay.time = date
        lastDay.add(Calendar.MONTH, 1)
        lastDay.set(DAY_OF_MONTH, 1)
        lastDay.add(DAY_OF_MONTH, -1)
        return lastDay.time
    }

    fun getFinalDayOfMonth(date: Date): Int {
        val lastDay = Calendar.getInstance()
        lastDay.time = date
        return lastDay.getMaximum(DAY_OF_MONTH)
    }

    fun addMonth(date: Date): Date {
        return addWithCalendar(date, Calendar.MONTH, 1)
    }

    fun subtractMonth(date: Date): Date {
        return addWithCalendar(date, Calendar.MONTH, -1)
    }

    private fun addWithCalendar(date: Date, field: Int, value: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(field, value)
        return calendar.time
    }

    fun now(): Date {
        return Date(System.currentTimeMillis())
    }


}
