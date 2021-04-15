package pl.setblack.jv;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Person {
    @NotNull
    private final String name;
    private final int age;
    private final boolean drinking;

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final int getAge() {
        return this.age;
    }

    public final boolean getDrinking() {
        return this.drinking;
    }

    public Person(@NotNull String name, int age, boolean drinking) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.age = age;
        this.drinking = drinking;
    }

    public Person(@NotNull String name, int age) {
        this(name, age, true);
    }

    public int hashCode() {
        String var10000 = this.name;
        int var1 = ((var10000 != null ? var10000.hashCode() : 0) * 31 + this.age) * 31;
        return var1 + (drinking ? 1 : 0);
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (var1 instanceof Person) {
                Person var2 = (Person) var1;
                if (Intrinsics.areEqual(this.name, var2.name) && this.age == var2.age && this.drinking == var2.drinking) {
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }

    @NotNull
    public String toString() {
        return "Person(name=" + this.name + ", age=" + this.age + ", drinking=" + this.drinking + ")";
    }

    @NotNull
    public final Person copy(@NotNull String name, int age, boolean drinking) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new Person(name, age, drinking);
    }

    @NotNull
    public final Person copy(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new Person(name, this.age, this.drinking);
    }
    @NotNull
    public final Person copy(int age) {
        return new Person(this.name, age, this.drinking);
    }
    @NotNull
    public final Person copy(boolean drinking) {
        return new Person(this.name, this.age, drinking);
    }

    @NotNull
    public final Person copy(@NotNull String name, int age) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new Person(name, age, this.drinking);
    }
    @NotNull
    public final Person copy(@NotNull String name, boolean drinking) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new Person(name, this.age, drinking);
    }
    @NotNull
    public final Person copy(int age, boolean drinking) {
        return new Person(this.name, age, drinking);
    }
}
