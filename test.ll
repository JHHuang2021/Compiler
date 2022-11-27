declare void @__builtin_print(i8* %s_0)
declare void @__builtin_println(i8* %s_0)
declare void @__builtin_printInt(i32 %int_0)
declare void @__builtin_printlnInt(i32 %int_0)
declare i32 @__builtin_getInt()
declare i8* @__builtin_getString()
declare i8* @__builtin_toString(i32 %int_0)
declare i8* @__builtin_malloc(i32 %size_0)
declare i8* @__builtin_str_add(i8* %lhs_0, i8* %rhs_1)
declare i1 @__builtin_str_eq(i8* %lhs_0, i8* %rhs_1)
declare i1 @__builtin_str_ne(i8* %lhs_0, i8* %rhs_1)
declare i1 @__builtin_str_gt(i8* %lhs_0, i8* %rhs_1)
declare i1 @__builtin_str_ge(i8* %lhs_0, i8* %rhs_1)
declare i1 @__builtin_str_lt(i8* %lhs_0, i8* %rhs_1)
declare i1 @__builtin_str_le(i8* %lhs_0, i8* %rhs_1)
declare i32 @__builtin_length(i8* %this_0)
declare i8* @__builtin_substring(i8* %this_0, i32 %left_1, i32 %right_2)
declare i32 @__builtin_parseInt(i8* %this_0)
declare i32 @__builtin_ord(i8* %this_0, i32 %pos_1)
define i32 @main()
{
main_0:
	%var_def_3 = alloca [2 x [3 x i32]]
}
