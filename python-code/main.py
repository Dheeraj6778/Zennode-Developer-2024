import math


catalogue = {"Product A": 20, "Product B": 40, "Product C": 50}

cartTotal = 0
giftWrapCost = 0
totalItems = 0
dic = dict()
for product in catalogue.keys():
    quantity = int(input(f"Enter the quantity for {product} \n") )
    totalItems += quantity
    price = catalogue[product]
    totalCost = price * quantity
    dic[product] = quantity
    cartTotal += totalCost
    gift = input("do you need gift wrap\n")
    if gift == "yes":
        giftWrapCost += quantity


def flat_10_discount():
    global cartTotal
    if cartTotal > 200:
        return cartTotal - 10
    return cartTotal


def bulk_5_discount():
    global dic
    total = 0
    for product in dic.keys():
        totalPrice = dic[product] * catalogue[product]
        if dic[product] > 10:
            total += totalPrice - 0.05 * totalPrice
        else:
            total += totalPrice
    return int(total)


def bulk_10_discount():
    global totalItems
    global cartTotal
    if totalItems > 20:
        return cartTotal - 0.1 * cartTotal
    return int(cartTotal)


def tiered_50_discount():
    global totalItems
    global dic
    global cartTotal
    if totalItems > 30:
        total = 0
        for product in dic.keys():
            if dic[product] > 15:
                extra = dic[product] - 15
                total += extra * catalogue[product] * 0.5
                total += 15 * catalogue[product]
            else:
                total += dic[product] * catalogue[product]
        return int(total)

    return cartTotal


totalPackages = math.ceil(totalItems / 10) * 5
shippingCost = totalPackages
totalCost = cartTotal + giftWrapCost + totalPackages

dic_discount = {
    "flat_10_discount": flat_10_discount(),
    "bulk_5_discount": bulk_5_discount(),
    "bulk_10_discount": bulk_10_discount(),
    "tiered_50_discount": tiered_50_discount(),
}
# sort based on value in ascending order
sorted_dic = sorted(dic_discount.items(), key=lambda x: x[1])
# get the first element of the sorted list
best_discount = sorted_dic[0]
discounted_cart = best_discount[1]
# total cost after adding shipping and gift wrapping
total_cost = discounted_cart + giftWrapCost + totalPackages
total_cost = int(total_cost)
for product in dic.keys():
    print(f"product: {product} quantity: {dic[product]} price: {catalogue[product]*dic[product]} \n")
print(f"subtotal : {cartTotal}")
print(f"discount type: {best_discount[0]} discount: {cartTotal - discounted_cart}\n")
print(f"shipping cost: {shippingCost} and gift wrap cost: {giftWrapCost}")
print(f"total cost: {total_cost}")